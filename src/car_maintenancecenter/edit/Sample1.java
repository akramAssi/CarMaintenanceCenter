/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.edit;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author akramassi
  */
class Sample1 
{
    
    final SimpleStringProperty col1;
    final SimpleStringProperty col2;
    final SimpleStringProperty col3;
    final SimpleStringProperty col4;
    final SimpleStringProperty col5;
    final SimpleStringProperty col6;
    SimpleStringProperty col7;
    
    Sample1(String id, String cnowner, String cnmanuf,String cmodel,String year,String ctdes,String col)
    {
            this.col1 = new SimpleStringProperty(id);
            this.col2 = new SimpleStringProperty(cnowner);
            this.col3 = new SimpleStringProperty(cnmanuf);
            this.col4 = new SimpleStringProperty(cmodel);
            this.col5 = new SimpleStringProperty(year);
            this.col6 = new SimpleStringProperty(ctdes);
            this.col7 = new SimpleStringProperty(col);
    }
   
        public String name()
        {
            return col2.getValue();
        }
        public String id()
        {
            return col1.getValue();
        }
        
    
}