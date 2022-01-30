package com.example.recipeappapi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.recipeappapi.R;
import com.example.recipeappapi.databinding.FragmentInstructionRecipeBinding;

public class InstructionRecipeFragment extends Fragment {


    private static final String ARG_TEXT = "instruction_text" ;
    private FragmentInstructionRecipeBinding instructionRecipeBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        instructionRecipeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_instruction_recipe,container,false);
        return instructionRecipeBinding.getRoot();
    }



    public static InstructionRecipeFragment newInstant(String instruction){

        InstructionRecipeFragment fragment = new InstructionRecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, instruction);
        fragment.setArguments(args);
        return fragment;


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String instruction = getArguments().getString(ARG_TEXT);

        instructionRecipeBinding.setInstructionRecipe(instruction);

    }
}
