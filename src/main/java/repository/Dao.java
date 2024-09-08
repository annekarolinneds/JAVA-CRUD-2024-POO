package repository;

import entity.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Dao<E extends Entity> implements IDao<E> {

    public static final String DB = "alphasystem";

    @Override
    public Long saveOrUpdate(E e) {

        Long id = 0L;

        if (e.getId() == null || e.getId() <= 0) {

            try (PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(getSaveStatement(), Statement.RETURN_GENERATED_KEYS)) {

                composeSaveOrUpdateStatement(preparedStatement, e);

                System.out.println(">> SQL: " + preparedStatement);

                preparedStatement.executeUpdate();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }

            } catch (Exception ex) {
                System.out.println(">> " + ex);
            }

        } else {
            try (PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(getUpdateStatement())) {

                composeSaveOrUpdateStatement(preparedStatement, e);

                System.out.println(">> SQL: " + preparedStatement);

                preparedStatement.executeUpdate();

                id = ((Entity) e).getId();

            } catch (Exception ex) {
                System.out.println("Exception: " + ex);
            }
        }

        return id;
    }

    @Override
    public E findById(Long id) {

        try (PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(getFindByIdStatement())) {

            preparedStatement.setLong(1, id);

            System.out.println(">> SQL: " + preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractObject(resultSet);
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        return null;
    }

    @Override
    public List<E> findAll() {

        try (PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(getFindAllStatement())) {

            System.out.println(">> SQL: " + preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            return extractObjects(resultSet);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        return null;
    }

    @Override
    public List<E> findAllActive(boolean active) {

        try (PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(getFindAllStatementActive())) {

            preparedStatement.setBoolean(1, active); ;

            System.out.println(">> SQL: " + preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            return extractObjects(resultSet);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {

        try (PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(getDeleteStatement())) {

            preparedStatement.setObject(1, id);

            System.out.println(">> SQL: " + preparedStatement);

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

    }

    @Override
    public List<E> extractObjects(ResultSet resultSet) {
        List<E> objects = new ArrayList<>();

        try {
            while (resultSet.next()) {
                objects.add(extractObject(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objects.isEmpty() ? null : objects;
    }

}
