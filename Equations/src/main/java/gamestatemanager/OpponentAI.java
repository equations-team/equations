package gamestatemanager;

import fundementalgamemechanics.Die;

import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

public class OpponentAI extends AbstractPlayer {

    /**
     * Will need to add Equation Constraint Solver when that is created
     */
    public OpponentAI(Manager gsm) {
        super(gsm);
    }

    /**
     * Will end up calling constraint solver when it is created
     */
    @Override
    public void takeTurn() {

        // Determine how the board state is different to conform to the Equation given by the ECS
        // I'm assuming the ECS gives me a string of the equation that can be made
        String ecsEquation = "";

        // First remove the dice that are in the required mat
        String afterRequired = removeCharactersFromString(ecsEquation, diceToFaces(gsm.getMyRequired().getMyMat()));
        // Then remove dice in the permitted mat
        String afterPermitted = removeCharactersFromString(afterRequired, diceToFaces(gsm.getMyPermitted().getMyMat()));
        // Remove the dice we still need from the possible list of dice to remove
        String afterResources = removeCharactersFromString(diceToFaces(gsm.getMyResources().getMyMat()), afterPermitted);

        if(afterResources.length() > 0) {
            //gsm.moveDie(this, index, decision)
            //gsm.moveDie(this, get the index of the dice, GameMove.ADDFORBIDDEN);
        } else {
            //gsm.moveDie(this, -1, GameMove.CHALLENGEIMPOSSIBLE);
        }
    }

    // These are some private helper methods to do string manipulation
    private static String removeCharactersFromString(String baseString, String toBeRemoved) {
        String toBeReturned = "";

        // Convert both Strings to char arrays and sort both of them
        char[] baseStringAsChars = baseString.toCharArray();
        char[] toBeRemoveAsChars = baseString.toCharArray();

        Arrays.sort(baseStringAsChars);
        Arrays.sort(toBeRemoveAsChars);

        // Similar to merge sort except take out characters that are the same in each string
        int base = 0;
        int removed = 0;

        // While the end of one String has not been found
        while (base < baseStringAsChars.length && removed < toBeRemoveAsChars.length) {
            if(baseStringAsChars[base] == toBeRemoveAsChars[removed]) {
                base++;
                removed++;
            } else if (baseStringAsChars[base] > toBeRemoveAsChars[removed]) {
                toBeReturned += toBeRemoveAsChars[removed];
                removed++;
            } else {
                toBeReturned += baseStringAsChars[base];
                base++;
            }
        }
        return toBeReturned;
    }

    // Turn Dice into a string containing their faces
    private static String diceToFaces(Vector<Die> dies) {
        return dies.stream().map( (dice) -> dice.getMyUpSide().getValue()).collect(Collectors.joining());
    }

}
