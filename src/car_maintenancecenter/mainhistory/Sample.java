/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.mainhistory;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author akramassi
  */
class Sample {
    final ObservableValue<Integer> id;
        final SimpleStringProperty cnowner;
        final SimpleStringProperty cnmanuf;
       final SimpleStringProperty cmodel;
       final SimpleStringProperty ctdes;
       final SimpleStringProperty ctech;
        final SimpleStringProperty ctarrived;
       final SimpleStringProperty ctstart;
       final SimpleStringProperty ctend;
       final SimpleStringProperty ctdel;
       SimpleStringProperty year;
       SimpleStringProperty type;
       int x=5;

        Sample(int id, String cnowner, String cnmanuf,String cmodel,String year,String ctdes,String ctech,String type,String ctarrived,String ctstart,String ctend,String ctdel) {
            this.id = new SimpleObjectProperty<>(id);
            this.cnowner = new SimpleStringProperty(cnowner);
            this.cnmanuf = new SimpleStringProperty(cnmanuf);
            this.cmodel = new SimpleStringProperty(cmodel);
            this.year = new SimpleStringProperty(year);
            this.ctdes = new SimpleStringProperty(ctdes);
            this.ctech = new SimpleStringProperty(ctech);
            this.ctarrived = new SimpleStringProperty(ctarrived);
            this.ctstart = new SimpleStringProperty(ctstart);
            this.ctend = new SimpleStringProperty(ctend);
            this.ctdel = new SimpleStringProperty(ctdel);
            
            this.type = new SimpleStringProperty(type);
        }
        public int gx()
        {
            return id.getValue();
        }

    
}