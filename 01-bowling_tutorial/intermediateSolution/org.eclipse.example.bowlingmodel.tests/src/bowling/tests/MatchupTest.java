/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package bowling.tests;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

import bowling.BowlingFactory;
import bowling.BowlingPackage;
import bowling.Game;
import bowling.League;
import bowling.Matchup;
import bowling.Player;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Matchup</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class MatchupTest extends TestCase {

	/**
	 * The fixture for this Matchup test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Matchup fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MatchupTest.class);
	}

	/**
	 * Constructs a new Matchup test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MatchupTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Matchup test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Matchup fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Matchup test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Matchup getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BowlingFactory.eINSTANCE.createMatchup());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}
	
	public void testCreateModelelement() {
		Player player = BowlingFactory.eINSTANCE.createPlayer();
		player.setName("Jonas");
		assertEquals("Jonas", player.getName());
		League league = BowlingFactory.eINSTANCE.createLeague();
		league.getPlayers().add(player);
		assertEquals(1, league.getPlayers().size());
	}

	public void testMatchupGameRef() {
		Matchup createMatchup = BowlingFactory.eINSTANCE.createMatchup();
		Game createGame = BowlingFactory.eINSTANCE.createGame();
		createMatchup.getGames().add(createGame);
		assertEquals(createGame.getMatchup(), createMatchup);
	}
	
	public void testEContainer() {
		Matchup createMatchup = BowlingFactory.eINSTANCE.createMatchup();
		Game createGame = BowlingFactory.eINSTANCE.createGame();
		createMatchup.getGames().add(createGame);
		assertEquals(createGame.eContainer(), createMatchup);
	}

	public void testReflectiveInformation() {
		League createLeague = BowlingFactory.eINSTANCE.createLeague();
		assertTrue(createLeague.eClass().getEAllReferences().get(0).isMany());
		assertTrue(BowlingPackage.eINSTANCE.getLeague_Players().isMany());
	}
	
	public void testReflection() {
		EObject eObject = BowlingFactory.eINSTANCE.createPlayer();
		eObject.eSet(BowlingPackage.eINSTANCE.getPlayer_Name(), "Jonas");
		Player player = (Player) eObject;
		assertEquals("Jonas", player.getName());
	}
	
	public void testReflection2(){
		Player player = BowlingFactory.eINSTANCE.createPlayer();
		player.setName("Jonas");
		for (EAttribute attribute: player.eClass().getEAllAttributes()) {
			player.eUnset(attribute);
		}
		assertEquals(null, player.getName());
	}	

	public void testValidation() {
		Matchup createMatchup = BowlingFactory.eINSTANCE.createMatchup();
		createMatchup.getGames().add(BowlingFactory.eINSTANCE.createGame());
		Diagnostic validate = Diagnostician.INSTANCE.validate(createMatchup);
		assertEquals(Diagnostic.ERROR, validate.getSeverity());
	}

	public void testCopy() {
		Player createPlayer = BowlingFactory.eINSTANCE.createPlayer();
		createPlayer.setName("Jonas");
		Player copy = EcoreUtil.copy(createPlayer);
		assertNotSame(createPlayer, copy);
		assertEquals(createPlayer.getName(), copy.getName());
	}
	
	
	

} //MatchupTest
