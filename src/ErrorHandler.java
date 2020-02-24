/**
 * ErrorHandler Class
 * [PART OF ALL TASKS, NOT REQUIRED]
 *
 * Used to treat exceptions. Not recommended.
 * Lazy
 */
public class ErrorHandler {

    /**
     * Enum containing errors for code readability
     */
    public enum Error{
        duplicateClient, duplicateDepot, duplicateVehicle,
        duplicateVehicleNoExit,
        duplicateClientNoExit,
        duplicateDepotNoExit,
        vehicleDepotAssignDuplicate,
        tourInvalidClientOrder,
        tourDuplicateClient,
        solutionInvalid,

        costsNullNodeCount,
        noCostsAssigned,

        cannotVisitAllLocations
    }

    /**
     * Method used to get String printed upon encountering an error
     * @param error value of the error (code)
     * @return String containing explicit error
     */
    public static String getErrorString(Error error){
        switch(error){
            case duplicateClient:             return "Client duplicate found";
            case duplicateDepot:              return "Depot duplicate found";
            case duplicateVehicle:            return "Vehicle duplicate found";
            case duplicateClientNoExit:       return "Client duplicate found, it will not be added to the problem";
            case duplicateDepotNoExit:        return "Depot duplicate found, it will not be added to the problem";
            case duplicateVehicleNoExit:      return "Vehicle duplicate found, it will not be added to the garage";
            case vehicleDepotAssignDuplicate: return "Vehicles can only be assigned to a single depot";
            case tourInvalidClientOrder:      return "Can only add increasing visiting times in tour";
            case tourDuplicateClient:         return "Cannot make tour go to the same client twice";
            case solutionInvalid:             return "Cannot generate solution to problem";
            case costsNullNodeCount:          return "Cannot generate costs, no node count assigned";
            case noCostsAssigned:             return "No costs assigned to costs matrix";
            case cannotVisitAllLocations:     return "Cannot visit all locations!";
            default:                          return "Unknown error";
        }
    }

    /**
     * Method handling an error reported by the program
     * @param error given error code
     */
    public static void handleError(Error error){
        System.out.println(ErrorHandler.getErrorString(error));
        if(!ErrorHandler.getErrorString(error).contains("it will not be added to the"))
            System.exit(error.ordinal());
    }
}
