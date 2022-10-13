package com.tca.TimeClockApp.Models;

import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
public class Response {
    //This class is used to generate a response payload to describe the response after an action was made

    private String statusType;
    private String responseMessage;
    private int responseCode;

    public Response(int responseCode){
        switch(responseCode){
            case 1:
                this.statusType = "Success";
                this.responseMessage = "The action was completed successfully";
                break;
            case 2:
                this.statusType = "Failed";
                this.responseMessage = "The action could not be completed";
                break;
            case 3:
                this.statusType = "Failed";
                this.responseMessage = "This username already exists";
        }

    }
}
