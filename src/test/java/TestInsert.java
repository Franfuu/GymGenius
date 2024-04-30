import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.entity.Client;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestInsert {
    public static void main(String[] args) throws SQLException {
        Client c = new Client(2,"Juanmaria","Popper","12345678","123456789","Mujer" );
        ClientDAO.save(c);
    }

}
