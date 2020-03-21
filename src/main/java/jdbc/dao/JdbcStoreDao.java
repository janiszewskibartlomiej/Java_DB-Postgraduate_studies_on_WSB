package jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcStoreDao extends JdbcDao {

	public JdbcStoreDao(Connection connection) throws SQLException {
		super(connection);
	}

	public List<String> list() throws SQLException {
		ResultSet result =  statement.executeQuery("SELECT store_id, address_id FROM store");

		List<String> stores = new ArrayList<>();

		while (result.next()) {
			int storeId = result.getInt("store_id");
			int addressId = result.getInt("address_id");
			String store = "Store id: " + storeId + ", Address id: " + addressId;
			stores.add(store);
		}
		return stores;
	}

	public List<String> listWithAddresses() throws SQLException {
		ResultSet result =  statement.executeQuery("SELECT s.store_id, a.address, a.district " +
				"FROM store s " +
				"LEFT JOIN address a ON s.address_id = a.address_id");

		List<String> stores = new ArrayList<>();

		while (result.next()) {
			int storeId = result.getInt("s.store_id");
			String address = result.getString("a.address");
			String district = result.getNString("a.district");

			String store = "Store id: " + storeId + ", Address: " + address + ", "  +	district;
			stores.add(store);
		}
		return stores;
	}

	public List<String> listWithFullAddresses() throws SQLException {
		ResultSet result =  statement.executeQuery("SELECT s.store_id, a.city_id, a.address, a.district, c.city, " +
						"co.country	FROM store s LEFT JOIN address a ON s.address_id = a.address_id" +
						"	JOIN city c ON a.city_id = c.city_id JOIN country co ON c.country_id = co.country_id");

		List<String> stores = new ArrayList<>();

		while (result.next()) {
			int storeId = result.getInt("s.store_id");
			String address = result.getString("a.address");
			String district = result.getNString("a.district");
			String city = result.getNString("c.city");
			String country = result.getNString("co.country");
			//Store id: 1, Address: 47 MySakila Drive, Alberta, Lethbridge, Canada
			String store = "Store id: " + storeId + ", Address: " + address + ", "  +	district + ", " + city + ", " + country;
			stores.add(store);
		}
		return stores;
	}

}
