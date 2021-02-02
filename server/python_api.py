from flask import Flask, jsonify,request
from flask_restful import Resource, Api
import urllib, json
import urllib.request
import sklearn
import joblib
import numpy
import pandas
import xgboost as xgb 
from sklearn.preprocessing import StandardScaler


app = Flask(__name__)
api = Api(app)

sc=joblib.load('standart_scaler2.bin') #standart scaler object

class Predict:

	def getResult(self,jsondata):
		 
		data = json.dumps(jsondata) 
		df = pandas.DataFrame(eval(data),index=[0])

		dataframe = sc.transform(df)
		dtest = xgb.DMatrix(dataframe)

		bst = xgb.Booster({'nthread': 3})  # init model
		bst.load_model('model.json') 

		print("model loaded")

		ypred = bst.predict(dtest)
		print(ypred)

		if ypred < 0.5 :
			return "A loan default will occur.This customer shouldn't get this amount of loan."
		else:
			return "The loan will be repaid.This customer can get this amount of loan."


class customerInfo(Resource):
	
	def post(self):
		customerInfo = request.get_json()
		predictor = Predict()
		return predictor.getResult(customerInfo)
	def get(self):
		isOnline = True;
		connection = json.dumps(isOnline)
		return isOnline





api.add_resource(customerInfo, '/')



if __name__ == '__main__':
	app.run(debug=True, host= '0.0.0.0')