/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Kendaraan;
import Model.KendaraanMobil;
import Model.KendaraanMotor;
import Model.ParkiranMobil;
import Model.ParkiranMotor;
import Model.TarifMobil;
import Model.TarifMotor;
import Model.Applications;
import Model.DaftarPegawai;
import View.Login;
import View.homeManager;
import View.homePeg;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author rahmatridhams
 */
public class HomePegController implements ActionListener {

    private homePeg hpc;
    private homeManager hmc;
    private Login log;
    private Applications apps;
    private String platnomors;

    public HomePegController(homePeg hpc, Applications appsi) {
        this.hpc = hpc;
        this.apps = appsi;

        hpc.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == hpc.getButTbh()) {
            platnomors = hpc.getTxtPlatNomorTbh().getText();
            System.out.println(platnomors);
            int index = hpc.getKendaraan2().getSelectedIndex();
            apps.addKendaraans(index, platnomors);
            System.out.println(platnomors);
            String bla = "";
            hpc.getTxtPlatNomorTbh().setText(bla);
            hpc.getTxtIsTambah().setText("Kendaraan berhasil ditambahkan");
        } else if (e.getSource() == hpc.getButKeluar()) {
            int pil = hpc.getKendaraan1().getSelectedIndex();
            platnomors = hpc.getTxtPlatNomor().getText();
            boolean isDenda=hpc.getCheckDenda().isSelected();
            apps.removeKendaraans(pil, platnomors, isDenda);
            hpc.getTxtBiaya().setText(""+apps.getBiaya());
            hpc.getTxtPlatNomor().setText("");
            
        } else if (e.getSource() == hpc.getButLogout()) {
            hpc.setVisible(false);

            LoginController login = new LoginController(apps);
        }  else if(e.getSource() == hpc.getButRefresh()){
            
            hpc.getHistoryKen().setText("");
            hpc.getHistoryKen().setText(apps.getHistoryParkir());
        }
    }
}
