package org.eclipse.example.bowling.tutorial.popup.actions;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;

import bowling.BowlingFactory;
import bowling.BowlingPackage;
import bowling.Matchup;
import bowling.Tournament;

public class ExampleTournamentDialog extends AbstractTournamentExampleDialog {
	Resource resource;
	NumberofMatchupListener numberofMatchupListener;
	private AdapterFactoryLabelProvider adapterFactoryLabelProvider;
	private AdapterFactoryContentProvider adapterFactoryContentProvider;

	@Override
	protected void loadContent(IFile file) throws IOException {
		// Load Tournament from file and set it with setTournament
		AdapterFactoryEditingDomain domain = new AdapterFactoryEditingDomain(getAdapterFactory(),
				new BasicCommandStack());
		resource = domain.createResource(file.getFullPath().toString());
		resource.load(null);
		EObject eObject = resource.getContents().get(0);
		setTournament((Tournament) eObject);
	}

	@Override
	protected void save() throws IOException {
		// save changes in the file
		resource.save(null);
	}

	@Override
	protected void addMatchup() {
		// add a new Matchup using a Command
		Matchup matchup = BowlingFactory.eINSTANCE.createMatchup();
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getTournament());
		Command command = AddCommand.create(editingDomain, getTournament(),
				BowlingPackage.eINSTANCE.getTournament_Matchups(), matchup);
		editingDomain.getCommandStack().execute(command);

	}

	@Override
	protected void undo() {
		// Undo the last change
		AdapterFactoryEditingDomain.getEditingDomainFor(getTournament()).getCommandStack().undo();
	}

	private final class NumberofMatchupListener extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeature().equals(BowlingPackage.eINSTANCE.getTournament_Matchups())) {
				updateNumberOfMatchups();
			}
			super.notifyChanged(msg);
		}
	}

	@Override
	protected void initializeListener() {
		// initialize a listener for the Label displaying the number of Matchups
		numberofMatchupListener = new NumberofMatchupListener();
		getTournament().eAdapters().add(numberofMatchupListener);
	}

	@Override
	protected void initializeTreeviewer(TreeViewer treeViewer) {
		// initialize a TreeViewer to show the Matchups and Games of the opened
		// Tournament
		adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(getAdapterFactory());
		adapterFactoryContentProvider = new AdapterFactoryContentProvider(getAdapterFactory());
		treeViewer.setLabelProvider(adapterFactoryLabelProvider);
		treeViewer.setContentProvider(adapterFactoryContentProvider);
		treeViewer.setInput(getTournament());
	}

	@Override
	public boolean close() {
		// remove all listeners
		adapterFactoryLabelProvider.dispose();
		adapterFactoryContentProvider.dispose();
		return super.close();
	}

	protected ExampleTournamentDialog(Shell parentShell) {
		super(parentShell);
	}

}
