package com.example.tim.dieseldensitycalculator;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private View thisView;
    NumberFormat prt;
    EditText edLStart;
    EditText edDensStart;
    EditText edKGStart;
    TextView textuWeightStart;
    TextView textuWeightCalc;
    TextView textDensCalc;
    TextView textLCalc;
    EditText edTempCalc;
    EditText edKGCalc;

    double DensStart;
    double KGStart;
    double TempCalc;
    double KGCalc;
    double DensCalc;
    double LitrCalc;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        thisView = inflater.inflate(R.layout.main_fragment,null);//, container, false);        return view;
        DensStart=820;
        KGStart=9000;
        KGCalc=9000;
        TempCalc=25;
        prt = NumberFormat.getInstance();

        edDensStart = thisView.findViewById(R.id.editDensStart);
        edDensStart.setFilters(new InputFilter[]{new InputFilterMinMax(800, 870)});

        edKGStart = thisView.findViewById(R.id.editKGStart);
        edKGStart.setFilters(new InputFilter[]{new InputFilterMinMax(1, 40000)});
        edLStart = thisView.findViewById(R.id.editLStart);
        edKGCalc = thisView.findViewById(R.id.editKGCalc);
        edKGCalc.setFilters(new InputFilter[]{new InputFilterMinMax(1, 40000)});
        textLCalc = thisView.findViewById(R.id.textLCalc);
        textuWeightStart = thisView.findViewById(R.id.textuWeightStart);
        textuWeightCalc = thisView.findViewById(R.id.textuWeightCalc);
        edTempCalc = thisView.findViewById(R.id.editDegree);
        edTempCalc.setFilters(new InputFilter[]{new InputFilterMinMax(-35, 50)});
        textDensCalc = thisView.findViewById(R.id.textDensCalc);
        uiSetStart();

        edDensStart.addTextChangedListener( new TextWatcher()  {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                uiUpdateCalc();
            }
        });

        edKGStart.addTextChangedListener( new TextWatcher()  {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                edKGCalc.setText(s);
                uiUpdateCalc();
            }
        });

        edTempCalc.addTextChangedListener( new TextWatcher()  {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                uiUpdateCalc();
            }
        });

        edKGCalc.addTextChangedListener( new TextWatcher()  {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                uiUpdateCalc();
            }
        });

        return thisView;
//inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    private double fnCalcTempCorrection (double Dens){
        double tCorr=0;
        if(Dens<660) tCorr=.000962;
        else if(Dens<670) tCorr=.000949;
        else if(Dens<680) tCorr=.000936;
        else if(Dens<690) tCorr=.000925;
        else if(Dens<700) tCorr=.000910;
        else if(Dens<710) tCorr=.000897;
        else if(Dens<720) tCorr=.000884;
        else if(Dens<730) tCorr=.000870;
        else if(Dens<740) tCorr=.000857;
        else if(Dens<750) tCorr=.000844;
        else if(Dens<760) tCorr=.000831;
        else if(Dens<770) tCorr=.000818;
        else if(Dens<780) tCorr=.000805;
        else if(Dens<790) tCorr=.000792;
        else if(Dens<800) tCorr=.000778;
        else if(Dens<810) tCorr=.000765;
        else if(Dens<820) tCorr=.000752;
        else if(Dens<830) tCorr=.000738;
        else if(Dens<840) tCorr=.000725;
        else if(Dens<850) tCorr=.000712;
        else if(Dens<860) tCorr=.000699;
        else if(Dens<870) tCorr=.000686;
        else if(Dens<880) tCorr=.000673;
        else if(Dens<890) tCorr=.000660;
        else if(Dens<900) tCorr=.000647;
        else if(Dens<910) tCorr=.000633;
        else if(Dens<920) tCorr=.000620;
        else if(Dens<930) tCorr=.000607;
        else if(Dens<940) tCorr=.000594;
        else if(Dens<950) tCorr=.000581;
        else if(Dens<960) tCorr=.000567;
        else if(Dens<970) tCorr=.000554;
        else if(Dens<980) tCorr=.000541;
        else if(Dens<990) tCorr=.000528;
        else if(Dens<=1)  tCorr=.000515;

        return tCorr*1000;
    }

    private void uiSetStart(){
        edDensStart.setText(prt.format(DensStart));
        edKGStart.setText(prt.format(KGStart) );
        edLStart.setText(prt.format((KGStart/DensStart)*1000));
        edKGCalc.setText(prt.format(KGCalc));
        textuWeightStart.setText(prt.format(DensStart*9.81));
        edTempCalc.setText(prt.format(TempCalc));
        edKGCalc.setText(prt.format(KGCalc));

    }

    private void uiUpdateCalc ()
    {
        DensStart=Double.parseDouble(edDensStart.getText().toString());
        String qwer=edKGStart.getText().toString().replaceAll(" ","");
        KGStart=Double.parseDouble(qwer);
        edLStart.setText(prt.format((KGStart/DensStart)*1000));
        textuWeightStart.setText(prt.format(DensStart*9.81));
        TempCalc=Double.parseDouble(edTempCalc.getText().toString());
        KGCalc=Double.parseDouble(edKGCalc.getText().toString().replaceAll(" ",""));
        DensCalc= DensStart + fnCalcTempCorrection(DensStart)*(20 - TempCalc) ;
        textDensCalc.setText(prt.format(DensCalc));
        textuWeightCalc.setText(prt.format(DensCalc*9.81));
        LitrCalc= KGCalc / DensCalc * 1000 ;
        textLCalc.setText(prt.format(LitrCalc));
    }
    // фильтр для ввода колва повторов
    public class InputFilterMinMax implements InputFilter {
        private int min;
        private int max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            //noinspection EmptyCatchBlock
            try {
                String strSource=source.toString().replaceAll(" ","");
                int input = Integer.parseInt(dest.subSequence(0, dstart).toString() + strSource + dest.subSequence(dend, dest.length()));
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }
        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }

}