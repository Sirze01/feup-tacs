/**
 */
package bowling.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.Diagnostician;

import bowling.BowlingFactory;
import bowling.Game;
import bowling.Matchup;

import junit.framework.TestCase;

import junit.textui.TestRunner;

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
	
	public void testValidation() {
		   Matchup matchup = BowlingFactory.eINSTANCE.createMatchup();
		   matchup.getGames().add(BowlingFactory.eINSTANCE.createGame());
		   Diagnostic validate = Diagnostician.INSTANCE.validate(matchup);
		   assertEquals(Diagnostic.ERROR, validate.getSeverity());
		}


} //MatchupTest
