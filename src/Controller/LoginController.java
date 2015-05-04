/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Applications;
import Model.DaftarPegawai;
import Model.KendaraanMobil;
import Model.KendaraanMotor;
import Model.Pegawai;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import View.Login;
import View.homeManager;
import View.homePeg;
import javafx.application.Application;

/**
 *
 * @author rahmatridhams
 */
public class LoginController implements ActionListener {

    private Login log;
    private homePeg hpc;
    private homeManager hmc;
    private Applications apps;
    private String usrnm;
    private String psswrd;

//    public LoginController(Login log, Applications appsi) {
//        this.apps = appsi;
//
//        log.addActionListener(this);
//        this.log=log;
//
//    }
    public LoginController(Applications appsi) {
        this.apps = appsi;

        this.log = new Login();
        log.setVisible(true);
        log.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(log.getButEnter())) {
            hpc = new homePeg();
            hmc = new homeManager();
            if (log.getPilUser().getSelectedIndex() == 2) {
                usrnm = (log.getTxtUsername()).getText();
                psswrd = (log.getTxtPassword()).getText();
                apps.setPegShift(apps.getDaftarPeg().findPegawai(usrnm, psswrd));
                if (apps.getPegShift() != null) {
                    log.setVisible(false);
                    hpc.setVisible(true);

                    HomePegController pegCon = new HomePegController(hpc, apps);
//                    hpc.addActionListener(pegCon);
                }
            } else if (log.getPilUser().getSelectedIndex() == 1) {
                if ((log.getTxtUsername()).getText().equals("admin") && (log.getTxtPassword()).getText().equals("admin")) {
                    log.setVisible(false);
                    hmc.show();

                    HomeManagerController mancon = new HomeManagerController(hmc, apps);
                    hmc.getTabelPeg().append(apps.getDaftarPeg().getDaftarP().toString());
                }
            } else {
                System.out.println("errorr");
            }
        }
    }
}
