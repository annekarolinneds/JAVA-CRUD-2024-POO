package repository;

import entity.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface IDao<E extends Entity> {

    public String getSaveStatement();

    public String getUpdateStatement();

    public String getFindByIdStatement();

    public String getFindAllStatement();

    public String getFindAllStatementActive();

    public String getDeleteStatement();

    public void composeSaveOrUpdateStatement(PreparedStatement stmt, E e);

    public E extractObject(ResultSet resultSet);

    public List<E> extractObjects(ResultSet resultSet);

    public Long saveOrUpdate(E e);

    public E findById(Long id);

    public List<E> findAll();

    public List<E> findAllActive(boolean active);

    public void deleteById(Long id);
}
