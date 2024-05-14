package utils;

import java.util.List;

import static utils.JsonToXML.*;

public class ExtractValues {

    static String xmlFilePath = "src/resources/XMLResponseData.xml";

    /**
     * Function that extract the decision flow id from the JSON
     * @return the value of the decision flow id
     */
    public static Integer getDecisionFlowId(){
        String JSON = convertXmlFileToJson(xmlFilePath);
        String decisionFlowIdJSONPath = "$.['soap:Envelope'].['soap:Body'].ProcessRequestResponse.Response.responseData.Results.ExecuteDecisionSmartResponse.ExecuteDecisionSmartResult.DecisionFlowId";
        return getIntegerJsonAttributeValue(decisionFlowIdJSONPath, JSON);
    }

    /**
     * Function that extract the SCORE from the JSON
     * @return the value of the SCORE
     */
    public static Integer getScore(){
        String JSON = convertXmlFileToJson(xmlFilePath);
        String decisionSmartResultsNamesJSONPath = "$.['soap:Envelope'].['soap:Body'].ProcessRequestResponse.Response.responseData.Results.ExecuteDecisionSmartResponse.ExecuteDecisionSmartResult.DecisionSmartResults[*].ResultOutput.Name";
        String scoreValueJSONPath = "";
        List<String> decisionSmartResultsNames = getList(decisionSmartResultsNamesJSONPath, JSON);
        for (int i=0; i<decisionSmartResultsNames.size(); i++) {
            if (decisionSmartResultsNames.get(i).equals("SCORE")) {
                scoreValueJSONPath = "$.['soap:Envelope'].['soap:Body'].ProcessRequestResponse.Response.responseData.Results.ExecuteDecisionSmartResponse.ExecuteDecisionSmartResult.DecisionSmartResults["+i+"].ResultOutput.Value";
                break;
            }
        }
        return getIntegerJsonAttributeValue(scoreValueJSONPath, JSON);
    }
}
