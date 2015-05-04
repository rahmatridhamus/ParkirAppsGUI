/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author rahmatridhams
 */
public class TarifMotor extends Tarif {
/**
 * Konstruktor turunan dari kelas Tarif
 */
    public TarifMotor() {
        
    }

    public TarifMotor(int jamAwal, int jamBerikut, int denda) {
        super(jamAwal, jamBerikut, denda);
    }
    
    
}
