# Bachelor's Project

This is my bachelors Project. Aim of this project is to compare machine learning algorithms for classification tasks 
which in this case was determining the loan defaulters, and develop a mobile application for Android platform which uses the best
ML model in the background. This project has a basic server and client structure. Mainly used coding languages are Python and 
Java.

## 1. Server Side

This part consists of data analysis and machine learning steps. 

### 1.1 Dataset

In this project two dataset is used. First on is [Lending Club Loan Dataset](https://www.kaggle.com/wordsforthewise/lending-club) dataset and 
second one is [German Credit Risk](https://www.kaggle.com/uciml/german-credit). Both dataset
are gathered from Kaggle Platform

### 1.2 Machine Learning

Compared macihne learning algorithms are K-Nearest Neighbor, Decision Tree , XGBoost , and Naive Bayes. 
After the experiments we see that XGBoost works the best. Therefore, XGBoost runs in backend of the andorid application.

### 1.3 Python API

In order to send and retrieve data between our machine learning model and android application, an REST API was developed. I used
[Python/Flask](https://flask.palletsprojects.com/en/1.1.x/) for development, and data retrieved and sent is in JSON format.

## 2. Client Side

This part focuses on the development of the android application. Platform used for development is Andorid Studio and the coding 
language is Java
