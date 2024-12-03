package com.example.controls.dao.services;

import com.example.controls.dao.CrudRegisterDao;
import com.example.controls.tda.list.*;
import com.example.models.CrudRegister;

public class CrudRegisterServices {
    private CrudRegisterDao register;

    public CrudRegisterServices() {
        this.register = new CrudRegisterDao();
    }

    public CrudRegister getRegister() {
        return this.register.getRegister();
    }

    public void setRegister(CrudRegister register) {
        this.register.setRegister(register);
    }

    public LinkedList getListAll() {
        return this.register.getListAll();
    }

    public Boolean save() throws Exception {
        return this.register.save();
    }

    public CrudRegister get(Integer id) throws Exception {
        return this.register.get(id);
    }
}
