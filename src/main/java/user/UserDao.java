package user;

import repository.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends Dao<User> {

    public static final String TABLE = "user";

    @Override
    public String getSaveStatement() {
        return "INSERT INTO " + TABLE
                + " (name, email, password, lastAccess, active) "
                + "VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "UPDATE " + TABLE
                + " SET name = ?, email = ?, password = ?, lastAccess = ?, active = ? "
                + "WHERE id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "SELECT id, name, email, password, lastAccess, active "
                + "FROM " + TABLE
                + " WHERE id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "SELECT id, name, email, password, lastAccess, active "
                + "FROM " + TABLE;
    }

    @Override
    public String getFindAllStatementActive() {
        return "SELECT id, name, email, password, lastAccess, active "
                + "FROM " + TABLE
                + " WHERE active = ?";
    }

    @Override
    public String getDeleteStatement() {
        return "DELETE "
                + "FROM " + TABLE
                + " WHERE id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement stmt, User e) {
        try {
            stmt.setString(1, e.getName());
            stmt.setString(2, e.getEmail());
            stmt.setString(3, e.getPassword());
            stmt.setObject(4, e.getLastAccess(), java.sql.Types.TIMESTAMP);
            stmt.setBoolean(5, e.isActive());

            if (e.getId() != null) {
                stmt.setLong(6, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User extractObject(ResultSet resultSet) {
        User user = null;

        try {
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setLastAccess(resultSet.getObject("lastAccess", LocalDateTime.class));
            user.setActive(resultSet.getBoolean("active"));
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

}
