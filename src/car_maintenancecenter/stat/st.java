/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.stat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author akramassi
 */
public class st {
    final ObservableValue<Integer> count;
     SimpleStringProperty name;
    final SimpleStringProperty address;
    
    public st(String name,int count)
    {
        this.count = new SimpleObjectProperty<>(count);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty("");
    }
    public String get()
    {
        return name.getValue();
    }
    public st(String count,String add)
    {
        this.count = new SimpleObjectProperty<>(0);
        this.address = new SimpleStringProperty(add);
        this.name = new SimpleStringProperty(count);
    }
    
    public SimpleStringProperty getNmae(){return name;}
    
}
