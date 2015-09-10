package com.madhu.api;

/**
 * @author Madhukar Reddy
 *
 */
public class PersonResponse {
    
    private Long id;
    private ResponseContent responseContent;
    
    public PersonResponse(PersonResponseBuilder builder) {
        this.id = builder.id;
        this.responseContent = builder.responseContent;
    
    }
    
    public static class PersonResponseBuilder {
        private Long id;
        private ResponseContent responseContent;
        
        public PersonResponseBuilder id(Long id) {
            this.id = id;
            return(this);
        }
        
    }

}
