/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Applications;
import View.Login;
import View.homeManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author rahmatridhams
 */
public class HomeManagerController implements ActionListener {

    private homeManager hmc;
    private Login log;
    private Applications apps;
    private String nama, username, pass, passCon, histPeg;

    public HomeManagerController(homeManager hmc, Applications apps) {
        this.hmc = hmc;
        this.apps = apps;

        hmc.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hmc.getButLogout()) {
            hmc.setVisible(false);

            LoginController login = new LoginController(apps);
        } else if (e.getSource() == hmc.getButTambah()) {
            this.nama = hmc.getTxtNamaLengkap().getText();
            this.username = hmc.getTxtUsrname().getText();
            this.pass = hmc.getTxtPassword().getText();
            this.passCon = hmc.getTxtCPassword().getText();

            apps.addPegawai(nama, username, pass, passCon);
            
            hmc.getisTerbuat().setText("Pegawai telah ditambahkan");
            hmc.getTxtNamaLengkap().setText("");
            hmc.getTxtUsrname().setText("");
            hmc.getTxtPassword().setText("");
            hmc.getTxtCPassword().setText("");
            

        } else if (e.getSource() == hmc.getButRekap()) {
                
              hmc.getTabelPeg().setText("");
//              System.out.println(apps.getPegawaihist());
              hmc.getTabelPeg().setText(apps.getPegawaihist());
            
//            apps.setPegawaihist(null);
//            hmc.getTabelPeg().append(apps.getPegawaihist());
//            apps.setPegawaihist(apps.getDaftarPeg().getDaftarP().toString());
//            System.out.println(apps.getDaftarPeg().getDaftarP().toString());
//            hmc.getTabelPeg().append(apps.getPegawaihist());
        } else if(e.getSource() == hmc.getButCetakHis()){
            hmc.getTabelHis().setText("");
            hmc.getTabelHis().setText(apps.getHistoryParkir());
        }
    }
}
