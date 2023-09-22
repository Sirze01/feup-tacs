/**
 */
package bowling.tests;

import bowling.BowlingFactory;
import bowling.BowlingPackage;
import bowling.Player;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Player</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PlayerTest extends TestCase {

	/**
	 * The fixture for this Player test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Player fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PlayerTest.class);
	}

	/**
	 * Constructs a new Player test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlayerTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Player test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Player fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Player test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Player getFixture() {
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
		setFixture(BowlingFactory.eINSTANCE.createPlayer());
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
	
	public void testReflection() {
		   EObject eObject = BowlingFactory.eINSTANCE.createPlayer();
		   eObject.eSet(BowlingPackage.eINSTANCE.getPlayer_Name(), "Jonas");
		   Player player = (Player) eObject;
		   assertEquals("Jonas", player.getName());
		}

	public void testCopy() {
		   Player player = BowlingFactory.eINSTANCE.createPlayer();
		   player.setName("Jonas");
		   Player copy = EcoreUtil.copy(player);
		   assertNotSame(player, copy);
		   assertEquals(player.getName(), copy.getName());
		}

} //PlayerTest
