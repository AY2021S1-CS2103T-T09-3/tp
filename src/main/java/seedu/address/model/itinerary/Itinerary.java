package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.model.attraction.Location;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;

/**
 * Represents an Itinerary in TrackPad.
 */
public class Itinerary {
    private final Name name;
    private final Description description;
    private final ItineraryDate startDate;
    private final ItineraryDate endDate;
    private final Budget budget;
    private final List<Day> days = new ArrayList<>();

    /**
     * Name must be present and not null.
     */
    public Itinerary(Name name, Description description, ItineraryDate startDate, ItineraryDate endDate,
                     Budget budget, List<Day> days) {
        requireAllNonNull(name, description, startDate, endDate, budget, days);

        checkArgument(startDate.isBefore(endDate), "Start date should come before end date");

        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        for (int i = 0; i < getNumberOfDays(); i++) {
            if (days.get(i) != null) {
                this.days.add(days.get(i));
            } else {
                this.days.add(new Day(Integer.toString(i + 1)));
            }
        }
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public ItineraryDate getStartDate() {
        return startDate;
    }

    public ItineraryDate getEndDate() {
        return endDate;
    }

    public Budget getBudget() {
        return budget;
    }

    public List<Day> getDays() {
        return days;
    }

    public Day getDay(Index day) {
        return this.days.get(day.getZeroBased());
    }

    public int getNumberOfDays() {
        assert startDate.isBefore(endDate);
        return ItineraryDate.daysBetween(startDate, endDate);
    }

    /**
     * Returns a string of all the locations of the itinerary attractions ordered by day.
     */
    public String getLocations() {
        List<Location> locations = new ArrayList<>();
        for (Day day : days) {
            for (ItineraryAttraction itineraryAttraction : day.getItineraryAttractions()) {
                Location location = itineraryAttraction.getAttraction().getLocation();
                if (!locations.contains(location)) {
                    locations.add(location);
                }
            }
        }
        return locations.stream().map(Object::toString).collect(Collectors.joining("-> "));
    }

    public List<ItineraryAttraction> getItineraryAttractions() {
        List<ItineraryAttraction> itineraryAttractions = new ArrayList<>();
        for (Day day : days) {
            itineraryAttractions.addAll(day.getItineraryAttractions());
        }
        return itineraryAttractions;
    }

    /**
     * Adds an itinerary attraction to the itinerary.
     */
    public void addItineraryAttraction(ItineraryAttraction toAdd, int day) {
        requireNonNull(toAdd);
        checkArgument(day > 0 && day <= getNumberOfDays(), "Day is not valid");
        days.get(day - 1).addItineraryAttraction(toAdd);
    }

    /**
     * Removes the equivalent itinerary attraction from the itinerary.
     * The itinerary attraction must exist in the list.
     */
    public void deleteItineraryAttraction(int index, int day) {
        checkArgument(day > 0 && (day <= getNumberOfDays()), "Day is not valid");
        days.get(day - 1).deleteItineraryAttraction(index);
    }

    /**
     * Edits the corresponding itinerary attraction in the itinerary.
     */
    // todo refine depending on usage
    public void editItineraryAttraction(ItineraryAttraction target, ItineraryAttraction editedItineraryAttraction,
                                        int day) {
        requireNonNull(editedItineraryAttraction);
        Day currentDay = days.get(day - 1);
        currentDay.editItineraryAttraction(target, editedItineraryAttraction);

    }

    /**
     * Returns true if an itinerary attraction in the itinerary list with the same identity as
     * {@code itineraryAttraction} exists.
     */
    public boolean contains(ItineraryAttraction itineraryAttraction) {
        requireNonNull(itineraryAttraction);
        for (Day day : days) {
            if (day.contains(itineraryAttraction)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both itineraries of the same name have the same start and end dates.
     * This defines a weaker notion of equality between two itineraries.
     */
    public boolean isSameItinerary(Itinerary otherItinerary) {
        if (otherItinerary == this) {
            return true;
        }

        return otherItinerary != null
                && otherItinerary.getName().equals(getName())
                && otherItinerary.getStartDate().equals(getStartDate())
                && otherItinerary.getEndDate().equals(getEndDate());
    }

    /**
     * Returns true if both itineraries have the same identity and data fields.
     * This defines a stronger notion of equality between two itineraries.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Itinerary)) {
            return false;
        }

        Itinerary otherItinerary = (Itinerary) other;
        return otherItinerary.getName().equals(getName())
                && otherItinerary.getDescription().equals(getDescription())
                && otherItinerary.getStartDate().equals(getStartDate())
                && otherItinerary.getEndDate().equals(getEndDate())
                && otherItinerary.getDays().equals(getDays());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, startDate, endDate, days);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Description: ")
                .append(getDescription())
                .append(" Start date: ")
                .append(getStartDate())
                .append(" End date: ")
                .append(getEndDate());
        for (int i = 1; i <= getNumberOfDays(); i++) {
            builder.append(" Day ").append(i).append(": ");
            getDays().get(i - 1).getItineraryAttractions().forEach(builder::append);
        }
        return builder.toString();
    }
}
