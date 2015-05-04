/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.LoginController;
import Model.Applications;
import Model.DaftarPegawai;
import Model.ParkiranMobil;
import Model.ParkiranMotor;
import Model.Pegawai;
import Model.TarifMobil;
import Model.TarifMotor;
import View.Login;

/**
 *
 * @author rahmatridhams
 */
public class Main {

    public static void main(String[] args) {
        Applications apps = new Applications();
        
//        Login login = new Login();
//        LoginController lc = new LoginController(login, apps);
        LoginController lc = new LoginController(apps);
//        login.setVisible(true);
        
        
    }

}
