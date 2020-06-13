/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.carhis;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author akramassi
  */
class Sample {
    final ObservableValue<Integer> id;
        final SimpleStringProperty cudesc;
        final SimpleStringProperty note;
       final SimpleStringProperty kilo;
       final SimpleStringProperty ctdes;
       final SimpleStringProperty ctech;
        final SimpleStringProperty ctarrived;
       final SimpleStringProperty ctstart;
       final SimpleStringProperty ctend;
       final SimpleStringProperty ctdel;
       SimpleStringProperty type;
       int x=5;

        Sample(int id, String cudesc,String ctdes,String ctech,String type, String note,String kilo,String ctarrived,String ctstart,String ctend,String ctdel) {
            this.id = new SimpleObjectProperty<>(id);
            this.cudesc = new SimpleStringProperty(cudesc);
            this.note = new SimpleStringProperty(note);
            this.kilo = new SimpleStringProperty(kilo);
//            this.year = new SimpleStringProperty(year);
            this.ctdes = new SimpleStringProperty(ctdes);
            this.ctech = new SimpleStringProperty(ctech);
            this.ctarrived = new SimpleStringProperty(ctarrived);
            this.ctstart = new SimpleStringProperty(ctstart);
            this.ctend = new SimpleStringProperty(ctend);
            this.ctdel = new SimpleStringProperty(ctdel);
            
            this.type = new SimpleStringProperty(type);
        }
        public Integer gx()
        {
            return id.getValue();
        }

    
}