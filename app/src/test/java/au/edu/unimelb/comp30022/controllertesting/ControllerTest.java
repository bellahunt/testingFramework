package au.edu.unimelb.comp30022.controllertesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import android.location.Location;

import static org.junit.Assert.*;

/**
 * Created by william on 31/8/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    private AddressTools addressToolsMock;
    private PostcodeValidator postcodeValidatorMock;
    private PostageRateCalculator postageRateCalculatorMock;

    @Before
    public void setUp() throws Exception {
        // create other mocks
        addressToolsMock = mock(AddressTools.class);
        postcodeValidatorMock = mock(PostcodeValidator.class);
        postageRateCalculatorMock = mock(PostageRateCalculator.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPostageCost() throws Exception {

    }

    @Test
    public void calculateButtonPressed() {
        // pass mock objects as dependency to constructor
        Controller controller = new Controller(addressToolsMock, postcodeValidatorMock, postageRateCalculatorMock);

        // configure mock expected interaction
        when(postageRateCalculatorMock.computeCost(any(Location.class), any(Location.class))).thenReturn(5);
        when(postcodeValidatorMock.isValid(any(String.class))).thenReturn(true);
        when(addressToolsMock.locationFromAddress(any(Address.class))).thenReturn(new Location("3000"));

        // call method being tested
        EditText sourcePostCodeText = new EditText();
        sourcePostCodeText.setText("Not null");
        controller.sourcePostCodeField = sourcePostCodeText;

        EditText destPostCodeText = new EditText();
        destPostCodeText.setText("Not null");
        controller.destinationPostCodeField = destPostCodeText;

        TextView costLabel = new TextView();
        costLabel.setText("Not null");
        controller.costLabel = costLabel;

        controller.calculateButtonPressed();

        // check if mock had expected interaction
        // computeCost method called in calculateButtonPressed, tests if directed correctly / does the right thing
        verify(postageRateCalculatorMock).computeCost(any(Location.class), any(Location.class));

        // assert results
        assertEquals("$5", controller.costLabel.getText());
    }
}