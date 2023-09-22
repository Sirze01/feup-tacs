package org.eclipse.example.bowling.tutorial.popup.actions;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import bowling.BowlingPackage;
import bowling.Player;

public class ExampleLeagueDialog extends AbstractLeagueExampleDialog {

    private Text text;
    private DataBindingContext context;
    private Binding bindValue;

    protected ExampleLeagueDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected void createNameComposite(Composite nameComposite) {
        Label label = new Label(nameComposite, SWT.NONE);
        label.setText("Name: ");
        text = new Text(nameComposite, SWT.NONE);
        context = new DataBindingContext();
    }

    @Override
    protected void setActivePlayer(Player firstElement) {
        if(bindValue!=null){
            bindValue.dispose();
        }
        text.setText("");
        ISWTObservableValue<String> observeText = WidgetProperties.text(SWT.Modify).observe(text);
        IObservableValue observeValue = EMFObservables.observeValue(firstElement, BowlingPackage.eINSTANCE.getPlayer_Name());
        bindValue = context.bindValue(observeText, observeValue);
    }
}
