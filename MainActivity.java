package com.example.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {


    ProgressDialog p;

    //URL url = new URL("http://192.168.1.26:5000");
    Boolean house_selected = false;
    Boolean employment_selected = false;
    Boolean purpose_selected = false;


    //customer instance
    Customer customer = new Customer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setScreen();
        buttonPredict();

    }

    public void buttonPredict(){

        Button buttonpredcit = (Button) findViewById(R.id.predictBtn);
        buttonpredcit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get url string
                String urladress = getIntent().getStringExtra("url_key");


                //------------SETTING OTHER PARAMETERS-------------
                int loanAmout = -1;
                float annualIncome = -1;
                float interestRate = -1;
                float dtiRatio = -1;
                int delay = -1;

                EditText loanAmountEditText = (EditText) findViewById(R.id.loanAmountEditText);
                EditText annualIncomeEditText = (EditText) findViewById(R.id.annualIncomeEditText);
                EditText interestRateEditText = (EditText) findViewById(R.id.interestRateEditText);
                EditText dtiRatioEditText = (EditText) findViewById(R.id.dtiEditText);
                EditText delayEditText = (EditText) findViewById(R.id.delayEditText);

                try {
                    loanAmout = Integer.parseInt(loanAmountEditText.getText().toString());
                    annualIncome = Float.parseFloat(annualIncomeEditText.getText().toString());
                    interestRate = Float.parseFloat(interestRateEditText.getText().toString());
                    dtiRatio = Float.parseFloat(dtiRatioEditText.getText().toString());
                    delay = Integer.parseInt(delayEditText.getText().toString());
                }
                catch(Exception e){
                    if(e instanceof NumberFormatException){
                        loanAmout = -1;
                        annualIncome = -1;
                        interestRate = -1;
                        dtiRatio = -1;
                        delay = -1;

                    }
                }
                if(annualIncome != -1 && loanAmout != -1 && interestRate != -1 && dtiRatio != -1 && delay != -1 && house_selected == true && purpose_selected == true && employment_selected == true){

                    customer.setLoan_amnt(loanAmout);
                    customer.setAnnual_inc(annualIncome);
                    customer.setInt_rate(interestRate);
                    customer.setDti(dtiRatio);
                    customer.setDelinq2_years(delay);

                    sendPostRequest(urladress);

                }else {
                    Toast toast = Toast.makeText(getApplicationContext(),"Please fill all the fields.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    toast.show();
                }

            }
        });

    }

    public void quitButton(){
        final AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(this);
        Button quitbtn = (Button) findViewById(R.id.quit_btn);
        quitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertdialogbuilder.setMessage("Are you sure?");
                alertdialogbuilder.setCancelable(true);

                alertdialogbuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertdialogbuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog quitDialog = alertdialogbuilder.create();
                quitDialog.show();

            }
        });
    }

    public void setMendatoryFields(){

        //--------------------SETTING THE MANDATORY FIELDS--------------------
        //---------loan amount mandatory field
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String colored = " *";

        TextView loanAmountTv = (TextView)findViewById(R.id.loanAmountTv);
        String simple = loanAmountTv.getText().toString();

        builder.append(simple);
        int start = builder.length();
        builder.append(colored);
        int end = builder.length();

        builder.setSpan(new ForegroundColorSpan(Color.RED), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        loanAmountTv.setText(builder);

        //------------------annual income mandatory field
        SpannableStringBuilder builder2 = new SpannableStringBuilder();
        String colored2 = " *";

        TextView annualincome = (TextView)findViewById(R.id.annualIncome_textView);
        String simple2 = annualincome.getText().toString();

        builder2.append(simple2);
        int start2 = builder2.length();
        builder2.append(colored2);
        int end2 = builder2.length();

        builder2.setSpan(new ForegroundColorSpan(Color.RED), start2, end2,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        annualincome.setText(builder2);
        //--------------------interest rate mandatory field
        SpannableStringBuilder builder3 = new SpannableStringBuilder();
        String colored3 = " *";

        TextView interestrate = (TextView)findViewById(R.id.interestRate_textView);
        String simple3 = interestrate.getText().toString();

        builder3.append(simple3);
        int start3 = builder3.length();
        builder3.append(colored3);
        int end3 = builder3.length();

        builder3.setSpan(new ForegroundColorSpan(Color.RED), start3, end3,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        interestrate.setText(builder3);
        //--------------------payment delay mandatory field
        SpannableStringBuilder builder4 = new SpannableStringBuilder();
        String colored4 = " *";

        TextView paymentdelay = (TextView)findViewById(R.id.paymentDelay_textView);
        String simple4 = paymentdelay.getText().toString();

        builder4.append(simple4);
        int start4 = builder4.length();
        builder4.append(colored4);
        int end4 = builder4.length();

        builder4.setSpan(new ForegroundColorSpan(Color.RED), start4, end4,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        paymentdelay.setText(builder4);
        //--------------------payment delay mandatory field
        SpannableStringBuilder builder5 = new SpannableStringBuilder();
        String colored5 = " *";

        TextView dtiratio = (TextView)findViewById(R.id.dtiRatio_textView);
        String simple5 = dtiratio.getText().toString();

        builder5.append(simple5);
        int start5 = builder5.length();
        builder5.append(colored5);
        int end5 = builder5.length();

        builder5.setSpan(new ForegroundColorSpan(Color.RED), start5, end5,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        dtiratio.setText(builder5);
        //--------------------payment delay mandatory field
        SpannableStringBuilder builder6 = new SpannableStringBuilder();
        String colored6 = " *";

        TextView employmentTv = (TextView)findViewById(R.id.employmentTextView);
        String simple6 = employmentTv.getText().toString();

        builder6.append(simple6);
        int start6 = builder6.length();
        builder6.append(colored6);
        int end6 = builder6.length();

        builder6.setSpan(new ForegroundColorSpan(Color.RED), start6, end6,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        employmentTv.setText(builder6);
        //--------------------payment delay mandatory field
        SpannableStringBuilder builder7 = new SpannableStringBuilder();
        String colored7 = " *";

        TextView houseStatus = (TextView)findViewById(R.id.houseStatusTextView);
        String simple7 = houseStatus.getText().toString();

        builder7.append(simple7);
        int start7 = builder7.length();
        builder7.append(colored7);
        int end7 = builder7.length();

        builder7.setSpan(new ForegroundColorSpan(Color.RED), start7, end7,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        houseStatus.setText(builder7);
        //--------------------payment delay mandatory field
        SpannableStringBuilder builder8 = new SpannableStringBuilder();
        String colored8 = " *";

        TextView purpose = (TextView)findViewById(R.id.purposeTextView);
        String simple8 = purpose.getText().toString();

        builder8.append(simple8);
        int start8 = builder8.length();
        builder8.append(colored8);
        int end8 = builder8.length();

        builder8.setSpan(new ForegroundColorSpan(Color.RED), start8, end8,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        purpose.setText(builder8);
  }

    public void setHouseParameters(){
      //--------------------SETTING HOUSE STATUS PARAMETERS-----------------
      Spinner house_spinner = (Spinner) findViewById(R.id.houseSpinner);
      house_spinner.setAdapter(new ArrayAdapter<Enum.House>(this, android.R.layout.simple_spinner_item, Enum.House.values()));
      house_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              Enum.House item = Enum.House.values()[position];

              customer.setHome_ownership_RENT(0);
              customer.setHome_ownership_MORTGAGE(0);
              customer.setHome_ownership_NONE(0);
              customer.setHome_ownership_OTHER(0);
              customer.setHome_ownership_OWN(0);
              switch (item) {
                  case RENT:
                      customer.setHome_ownership_RENT(1);
                      house_selected = true;
                      break;
                  case MORTAGE:
                      customer.setHome_ownership_MORTGAGE(1);
                      house_selected = true;
                      break;

                  case NONE:
                      customer.setHome_ownership_NONE(1);
                      house_selected = true;
                      break;
                  case OTHER:
                      customer.setHome_ownership_OTHER(1);
                      house_selected = true;
                      break;
                  case OWN:
                      customer.setHome_ownership_OWN(1);
                      house_selected = true;
                      break;
                  case CHOOSE:
                      house_selected = false;
                      break;
              }
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
              //please select?
          }
      });
  }

    public void setEmploymentParameters(){

      //--------------------SETTING EMPLOYMENT TIME PARAMETERS-----------------
      Spinner employment_spinner = (Spinner) findViewById(R.id.employmentSpinner);
      employment_spinner.setAdapter(new ArrayAdapter<Enum.EmploymentLen>(this, android.R.layout.simple_spinner_item, Enum.EmploymentLen.values()));

      employment_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              Enum.EmploymentLen item = Enum.EmploymentLen.values()[position];
              customer.setEmp_length_1less_years(0);
              customer.setEmp_length_2_years(0);
              customer.setEmp_length_3_years(0);
              customer.setEmp_length_4_years(0);
              customer.setEmp_length_5_years(0);
              customer.setEmp_length_6_years(0);
              customer.setEmp_length_7_years(0);
              customer.setEmp_length_8_years(0);
              customer.setEmp_length_9_years(0);
              customer.setEmp_length_10more_years(0);
              switch (item) {
                  case TENYEARSANDMORE:
                      customer.setEmp_length_10more_years(1);
                      employment_selected = true;
                      break;
                  case TWOYEARS:
                      customer.setEmp_length_2_years(1);
                      employment_selected = true;
                      break;
                  case THREEYEARS:
                      customer.setEmp_length_3_years(1);
                      employment_selected = true;
                      break;
                  case FOURYEARS:
                      customer.setEmp_length_4_years(1);
                      employment_selected = true;
                      break;
                  case FIVEYEARS:
                      customer.setEmp_length_5_years(1);
                      employment_selected = true;
                      break;
                  case SIXYEARS:
                      customer.setEmp_length_6_years(1);
                      employment_selected = true;
                      break;
                  case SEVENYEARS:
                      customer.setEmp_length_7_years(1);
                      employment_selected = true;
                      break;

                  case EIGHTYEARS:
                      customer.setEmp_length_8_years(1);
                      employment_selected = true;
                      break;

                  case NINEYEARS:
                      customer.setEmp_length_9_years(1);
                      employment_selected = true;
                      break;
                  case ONEANDLESS:
                      customer.setEmp_length_1less_years(1);
                      employment_selected = true;
                      break;
                  case CHOOSE:
                      employment_selected = false;
                      break;
              }
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
              //please select?
          }
      });

  }

    public void setPurposeParameters(){

      //--------------------SETTING PURPOSE PARAMETERS-----------------
       Spinner purpose_spinner = (Spinner) findViewById(R.id.purposeSpinner);
      purpose_spinner.setAdapter(new ArrayAdapter<Enum.Purpose>(this, android.R.layout.simple_spinner_item, Enum.Purpose.values()));
      purpose_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              Enum.Purpose item = Enum.Purpose.values()[position];

              customer.setPurpose_home_improvement(0);
              customer.setPurpose_credit_card(0);
              customer.setPurpose_debt_consolidation(0);
              customer.setPurpose_educational(0);
              customer.setPurpose_house(0);
              customer.setPurpose_major_purchase(0);
              customer.setPurpose_medical(0);
              customer.setPurpose_moving(0);
              customer.setPurpose_other(0);
              customer.setPurpose_renewable_energy(0);
              customer.setPurpose_small_business(0);
              customer.setPurpose_vacation(0);
              customer.setPurpose_wedding(0);
               switch (item) {
                  // TODO: rivate setDefaults();
                  case HOME_IMPROVMENT:
                      customer.setPurpose_home_improvement(1);
                      purpose_selected = true;
                      break;
                  case HOUSE:
                      customer.setPurpose_house(1);
                      purpose_selected = true;
                      break;
                      //TODO: break;
                  case OTHER:
                      customer.setPurpose_other(1);
                      purpose_selected = true;
                      break;
                  case MOVING:
                      customer.setPurpose_moving(1);
                      purpose_selected = true;
                      break;
                  case MEDICAL:
                      customer.setPurpose_medical(1);
                      purpose_selected = true;
                      break;
                  case WEDDING:
                      customer.setPurpose_wedding(1);
                      purpose_selected = true;
                      break;
                  case VACATION:
                      customer.setPurpose_vacation(1);
                      purpose_selected = true;
                      break;
                  case CREDIT_CARD:
                      customer.setPurpose_credit_card(1);
                      purpose_selected = true;
                      break;
                  case EDUCATIONAL:
                      customer.setPurpose_educational(1);
                      purpose_selected = true;
                      break;
                  case MAJOR_PURPOSES:
                      customer.setPurpose_major_purchase(1);
                      purpose_selected = true;
                      break;
                  case SMALL_BUSINESS:
                      customer.setPurpose_small_business(1);
                      purpose_selected = true;
                      break;
                  case RENEWABLE_ENERGY:
                      customer.setPurpose_renewable_energy(1);
                      purpose_selected = true;
                      break;
                  case DEPT_CONSIDILATION:
                      customer.setPurpose_debt_consolidation(1);
                      purpose_selected = true;
                      break;
                  case CHOOSE:
                      purpose_selected = false;
                      break;

              }
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
              //please select?
          }
      });
  }

    public void setQuestionMarkButtons(){
      //----------------- QUESTION MARK BUTTONS-------------------

        ((ImageButton)findViewById(R.id.loanAmountQuestion)).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        Toast toast = Toast.makeText(getApplicationContext(),"The listed amount of the loan applied for by the borrower.", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.show();

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ((ImageButton)findViewById(R.id.annualIncomeQuestion)).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        Toast toast = Toast.makeText(getApplicationContext(),"The self-reported annual income provided by the borrower during registration.", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.show();

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ((ImageButton)findViewById(R.id.interestRateQuestion)).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        Toast toast = Toast.makeText(getApplicationContext(),"Interest Rate on the loan.", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.show();

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ((ImageButton)findViewById(R.id.paymentDelayQuestion)).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        Toast toast = Toast.makeText(getApplicationContext(),"The number of 30+ days past-due incidences of delinquency in the borrower's credit file for the past 2 years.", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.show();

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ((ImageButton)findViewById(R.id.dtiQuestion)).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        Toast toast = Toast.makeText(getApplicationContext(),"A ratio calculated using the borrower’s total monthly debt payments, excluding mortgage and the requested LC loan, divided by the borrower’s self-reported monthly income.", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.show();

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ((ImageButton)findViewById(R.id.employmentLenQuestion)).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        Toast toast = Toast.makeText(getApplicationContext(),"Employment length in years.", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.show();

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ((ImageButton)findViewById(R.id.houseQuestion)).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        Toast toast = Toast.makeText(getApplicationContext(),"The home ownership status provided by the borrower.", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.show();

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        ((ImageButton)findViewById(R.id.purposeQuestion)).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton ) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        Toast toast = Toast.makeText(getApplicationContext(),"Purpose of the loan request.", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.show();

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });




      /*Button purposequestion = (Button) findViewById(R.id.purposeQuestion);
      purposequestion.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast toast = Toast.makeText(getApplicationContext(),"Purpose of the loan request.", Toast.LENGTH_LONG);
              toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
              toast.show();
          }
      }); */

  }

    public void setScreen(){
      setMendatoryFields();
      setHouseParameters();
      setEmploymentParameters();
      setPurposeParameters();
      setQuestionMarkButtons();
      quitButton();
  }

    public void sendPostRequest(String url){
        Gson gson = new Gson();
        String json = gson.toJson(customer);
        HttpPost postRequest = new HttpPost(url);
        postRequest.execute(json);
    }

    public void setAlertDialog(String result){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(result);
        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
     }

    class HttpPost extends AsyncTask<String, Void, Void> {
        String inputLine;
        String result;
        String data;
        String urlinfo;
        JSONObject json;
        int statuscode;

        public HttpPost(String url){
            this.urlinfo = url;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(MainActivity.this);
            p.setMessage("Please wait...");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected Void doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(urlinfo);
            } catch (Exception e) {
                    if(e instanceof MalformedURLException){
                        e.printStackTrace();
                    }
            }
            HttpURLConnection conn = null;
            try {
                data = strings[0];
                json = new JSONObject(data);

                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("POST");

                if (this.json != null) {
                    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                    writer.write(data);//json.toString());
                    writer.flush();
                }

                statuscode = conn.getResponseCode();
                if (statuscode == 200) {

                    InputStreamReader streamReader = new
                            InputStreamReader(conn.getInputStream());
                    //Create a new buffered reader and String Builder
                    BufferedReader reader = new BufferedReader(streamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    //Check if the line we are reading is not null
                    while ((inputLine = reader.readLine()) != null) {
                        stringBuilder.append(inputLine);
                    }
                    //Close our InputStream and Buffered reader
                    reader.close();
                    streamReader.close();
                    //Set our result equal to our stringBuilder
                    result = stringBuilder.toString();
                    //customer = new Customer();
                }
                return null;
            }
            catch (IOException e) {
                    e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            p.dismiss();  //ProgresDialog u kapatıyoruz.

            if(statuscode == 200){

                //Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
                setAlertDialog(result);
                MainActivity.this.setContentView(R.layout.activity_main);
                MainActivity.this.setScreen();
                customer = new Customer();
                house_selected = false;
                employment_selected = false;
                purpose_selected = false;
                MainActivity.this.buttonPredict();

            } else {
                setAlertDialog("Something wrong with URL.Please check your URL.");
            }

        }

    }

}