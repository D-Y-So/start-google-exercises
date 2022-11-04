public class Main {
    public static void main(String[] args) {
        HttpFacade httpFacade = new HttpFacade();

        System.out.println("HTTP GET:\n" + httpFacade.request("https://reqres.in/api/users/3", HTTPRequest.GET,""));
        System.out.println(httpFacade.request("https://reqres.in/api/users/23",HTTPRequest.GET,""));
        System.out.println(httpFacade.request("https://reqres.in/api/users?page=2", HTTPRequest.GET,""));
        System.out.println(httpFacade.request("https://reqres.in/api/users?delay=3", HTTPRequest.GET,""));

        String json = "{\"email\":\"eve.holt@reqres.in\",\"password\":\"dfdgfh\"}";
        System.out.println("HTTP POST: " + httpFacade.request("https://reqres.in/api/register",HTTPRequest.POST,json));

        json= "{\"name\":\"morpheus\",\"job\":\"zion resident\"}";
        System.out.println("HTTP PUT: " + httpFacade.request("https://reqres.in/api/users/2",HTTPRequest.PUT,json));

        json= "{\"name\":\"Neo\",\"job\":\"The One\"}";
        System.out.println("HTTP PATCH: " + httpFacade.request("https://reqres.in/api/users/2",HTTPRequest.PATCH,json));

        System.out.println("HTTP DELETE: " + httpFacade.request("https://reqres.in/api/users/2",HTTPRequest.DELETE,""));
    }
}