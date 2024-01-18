package org.vaadin.example;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@PageTitle("Lab2 - A3")
public class MainView extends VerticalLayout {

    private Tab1View tab1View;
    private Tab2View tab2View;
    private Tab3View tab3View;

    public MainView() {
        tab1View = new Tab1View();
        tab2View = new Tab2View();
        tab3View = new Tab3View();

        Tab tab1 = new Tab("Tab 1");
        Tab tab2 = new Tab("Tab 2");
        Tab tab3 = new Tab("Tab 3");
        Tabs tabs = new Tabs(tab1, tab2, tab3);

        // Inicialmente, solo Tab1View es visible
        tab1View.setVisible(true);
        tab2View.setVisible(false);
        tab3View.setVisible(false);

        add(tabs, tab1View, tab2View, tab3View);

        // Opcional: Si también deseas centrar los componentes verticalmente en el layout
        setAlignItems(Alignment.CENTER);

        tabs.addSelectedChangeListener(event -> {
            Tab selectedTab = tabs.getSelectedTab();
            
            tab1View.setVisible(selectedTab == tab1);
            tab2View.setVisible(selectedTab == tab2);
            tab3View.setVisible(selectedTab == tab3);
        });
    }
}



