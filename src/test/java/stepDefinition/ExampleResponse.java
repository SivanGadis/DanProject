package stepDefinition;

public class ExampleResponse {
    private Result[] results;

    //add getters and setters

    public class Result {
    	public String JWT_Token;
    	public String reset_token;
    	public String invitation_code;
    	public Integer user_id;
    	public Boolean confirmed;
        //add getters and setters
    }
}
