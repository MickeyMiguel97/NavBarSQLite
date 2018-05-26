package com.example.yanirayanes.navbar_sqlite.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.yanirayanes.navbar_sqlite.R;
import com.example.yanirayanes.navbar_sqlite.db.DBhelper;
import com.example.yanirayanes.navbar_sqlite.objeto.persona;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link agregar_persona.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link agregar_persona#newInstance} factory method to
 * create an instance of this fragment.
 */
public class agregar_persona extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private int counter = 0;
    private persona p;
    private Button btn_agregar;
    private ImageView ImVi_icp;
    private EditText EdTe_carnet, EdTe_nombre;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public agregar_persona() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment agregar_persona.
     */
    // TODO: Rename and change types and number of parameters
    public static agregar_persona newInstance(String param1, String param2) {
        agregar_persona fragment = new agregar_persona();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.p = new persona();
        btn_agregar = btn_agregar.findViewById(R.id.btn_agregar);
        ImVi_icp = ImVi_icp.findViewById(R.id.ImVi_icp);
        EdTe_carnet = EdTe_carnet.findViewById(R.id.EdTe_carnet);
        EdTe_nombre = EdTe_nombre.findViewById(R.id.EdTe_nombre);
        Bundle args = new Bundle();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_persona, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = DBhelper.myDB.add(new persona(
                     EdTe_carnet.getText().toString(),
                     EdTe_nombre.getText().toString(),
                     0
             ));
             if(flag){
                 Log.d("ADD", EdTe_nombre.getText().toString());
             }
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     *
     *
     *
     *
     *
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
