package seedu.address.model.itinerary;

import java.util.Objects;

import seedu.address.model.attraction.Attraction;
import seedu.address.model.commons.Name;

/**
 * Wrapper class to contain attributes of an attraction specific to a particular Itinerary.
 */
public class ItineraryAttraction {
    private Attraction attraction;
    //todo add start time, end time, budget etc. And also maybe extend from Attraction directly.

    public ItineraryAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public Name getName() {
        return attraction.getName();
    }

    public Attraction getAttraction() {
        return attraction;
    }

    /**
     * Returns true if both itinerary attractions are of the same name have at least one other identity field
     * that is the same.
     * This defines a weaker notion of equality between two itinerary attractions.
     */
    public boolean isSameItineraryAttraction(ItineraryAttraction otherItineraryAttraction) {
        if (otherItineraryAttraction == this) {
            return true;
        } else if (otherItineraryAttraction instanceof ItineraryAttraction) {
            ItineraryAttraction itineraryAttraction = (ItineraryAttraction) otherItineraryAttraction;
            // todo add more fields
            return itineraryAttraction.getAttraction().isSameAttraction(attraction);
        } else {
            return false;
        }
    }

    /**
     * Returns true if both itinerary attractions have the same identity and data fields.
     * This defines a stronger notion of equality between two itinerary attractions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ItineraryAttraction)) {
            return false;
        }

        ItineraryAttraction otherItineraryAttraction = (ItineraryAttraction) other;
        // todo add more fields
        return otherItineraryAttraction.getAttraction().equals(getAttraction());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(attraction);
    }

    @Override
    public String toString() {
        return attraction.toString();
        // todo add more fields
    }
}
