package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_ZOO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ZOO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ZOO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ZOO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.MBS;
import static seedu.address.testutil.TypicalAttractions.SUNTEC;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AttractionBuilder;

public class AttractionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Attraction attraction = new AttractionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> attraction.getTags().remove(0));
    }

    @Test
    public void isSameAttraction() {
        // same object -> returns true
        assertTrue(MBS.isSameAttraction(MBS));

        // null -> returns false
        assertFalse(MBS.isSameAttraction(null));

        // different phone and email -> returns false
        Attraction editedMBS = new AttractionBuilder(MBS).withPhone(VALID_PHONE_ZOO).withEmail(VALID_EMAIL_ZOO).build();
        assertFalse(MBS.isSameAttraction(editedMBS));

        // different name -> returns false
        editedMBS = new AttractionBuilder(MBS).withName(VALID_NAME_ZOO).build();
        assertFalse(MBS.isSameAttraction(editedMBS));

        // same name, same phone, different attributes -> returns true
        editedMBS= new AttractionBuilder(MBS).withEmail(VALID_EMAIL_ZOO).withAddress(VALID_ADDRESS_ZOO)
                .withTags(VALID_TAG_SIGHTSEEING).build();
        assertTrue(MBS.isSameAttraction(editedMBS));

        // same name, same email, different attributes -> returns true
        editedMBS = new AttractionBuilder(MBS).withPhone(VALID_PHONE_ZOO).withAddress(VALID_ADDRESS_ZOO)
                .withTags(VALID_TAG_SIGHTSEEING).build();
        assertTrue(MBS.isSameAttraction(editedMBS));

        // same name, same phone, same email, different attributes -> returns true
        editedMBS = new AttractionBuilder(MBS).withAddress(VALID_ADDRESS_ZOO).withTags(VALID_TAG_SIGHTSEEING).build();
        assertTrue(MBS.isSameAttraction(editedMBS));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Attraction MBSCopy = new AttractionBuilder(MBS).build();
        assertTrue(MBS.equals(MBSCopy));

        // same object -> returns true
        assertTrue(MBS.equals(MBS));

        // null -> returns false
        assertFalse(MBS.equals(null));

        // different type -> returns false
        assertFalse(MBS.equals(5));

        // different person -> returns false
        assertFalse(MBS.equals(SUNTEC));

        // different name -> returns false
        Attraction editedSUNTEC = new AttractionBuilder().withName(VALID_NAME_ZOO).build();
        assertFalse(SUNTEC.equals(editedSUNTEC));

        // different phone -> returns false
        editedSUNTEC = new AttractionBuilder(SUNTEC).withPhone(VALID_PHONE_ZOO).build();
        assertFalse(SUNTEC.equals(editedSUNTEC));

        // different email -> returns false
        editedSUNTEC = new AttractionBuilder(SUNTEC).withEmail(VALID_EMAIL_ZOO).build();
        assertFalse(SUNTEC.equals(editedSUNTEC));

        // different address -> returns false
        editedSUNTEC = new AttractionBuilder(SUNTEC).withAddress(VALID_ADDRESS_ZOO).build();
        assertFalse(MBS.equals(editedSUNTEC));

        // different tags -> returns false
        editedSUNTEC = new AttractionBuilder(SUNTEC).withTags(VALID_TAG_SIGHTSEEING).build();
        assertFalse(MBS.equals(editedSUNTEC));
    }
}
