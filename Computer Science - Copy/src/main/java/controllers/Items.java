package controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("items/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class Items{

    @GET
    @Path("list")
    public String ItemsList() {
        System.out.println("Invoked Items.Items.List()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT ItemName FROM Items ORDER BY ItemName ASC");
            ResultSet results = ps.executeQuery();
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("ItemName", results.getString(1));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }
    @GET
    @Path("one/{ItemName}")
    public String ItemsOne() {
        System.out.println("Invoked Items.Items.One()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT ItemName FROM Items WHERE ItemName = ?");
            ResultSet results = ps.executeQuery();
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("ItemName", results.getString(1));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }
    @POST
    @Path("add")
    public String ItemsAdd(@FormDataParam("ItemID") Integer ItemID, @FormDataParam("ItemName") String ItemName, @FormDataParam("SubType") String SubType) {
        System.out.println("Invoked Items.ItemsAdd()");
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Items (ItemID, ItemName, SubType) VALUES (?, ?, ?)");
            ps.setInt(1, ItemID);
            ps.setString(2, ItemName);
            ps.setString(3, SubType);
            ps.execute();
            return "{\"OK\": \"Added Item.\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to create new item, please see server console for more info.\"}";
        }

    }
    @POST
    @Path("update")
    public String updateItems(@FormDataParam("ItemID") Integer ItemID, @FormDataParam("ItemName") String ItemName, @FormDataParam("Lore") String Lore, @FormDataParam("Class") String Class) {
        try {
            System.out.println("Invoked Items.UpdateItems/update ItemID=" + ItemID);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Items SET ItemName = ? WHERE ItemID = ?");
            ps.setString(1, ItemName);
            ps.setInt(2, ItemID);
            ps.setString(3, Lore);
            ps.setString(4, Class);
            ps.execute();
            return "{\"OK\": \"Items updated\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }
    @POST
    @Path("delete/{ItemID}")
    public String DeleteItem(@PathParam("ItemID") Integer ItemID) throws Exception {
        System.out.println("Invoked Items.DeleteItem()");
        if (ItemID == null) {
            throw new Exception("ItemID is missing in the HTTP request's URL.");
        }
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Items WHERE ItemID = ?");
            ps.setInt(1, ItemID);
            ps.execute();
            return "{\"OK\": \"Item deleted\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }
    @GET
    @Path("specificlist/{ItemType}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String SpecificList(@PathParam("ItemType")String ItemType) {
        System.out.println("Invoked Items.Items.SpecificList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT ItemName FROM Items WHERE ItemType = ? ORDER BY ItemName ASC");
            ps.setString(1, ItemType);
            ResultSet results = ps.executeQuery();
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("ItemName", results.getString(1));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }
}
