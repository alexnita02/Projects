package DataAccess;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.ConnectionFactory;

/**
 * clasa generica pentru operatiile asupra unui obiect de tip T
 * ne ofera metode pentru inserare,stergere,updatare si cautare dupa ID in Baza de date utilizand JDBC
 * @param <T>
 */
public class AbstractDAO<T> {


    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO(){
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }


    /**
     * inserarea unui obiect in baza de date
     * @param abstractObject obiectul care va fi inserat
     */

    public void insert(Object abstractObject) {

        String tableName = abstractObject.getClass().getSimpleName();
        Field[] allFields = abstractObject.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder();
        StringBuilder values = new StringBuilder();
        query.append("Insert INTO `").append(tableName).append("` (");

        try {
            for (int i = 0; i < allFields.length - 1; i++) {
                allFields[i].setAccessible(true);
                query.append(allFields[i].getName());
                query.append(", ");
                Object value = allFields[i].get(abstractObject);
                String fieldType = allFields[i].getType().getSimpleName();
                if (fieldType.equals("String"))
                    values.append("\"").append(value).append("\"");
                else
                    values.append(value);
                values.append(", ");
            }

            int lastFieldIndex = allFields.length - 1;
            allFields[lastFieldIndex].setAccessible(true);
            query.append(allFields[lastFieldIndex].getName());
            Object value = allFields[lastFieldIndex].get(abstractObject);
            String fieldType = allFields[lastFieldIndex].getType().getSimpleName();
            if (fieldType.equals("String"))
                values.append("\"").append(value).append("\"");
            else
                values.append(value);
            query.append(") values (").append(values).append(" )");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at inserting");
        }
        try {
            Connection conection = ConnectionFactory.getConnection();
            PreparedStatement prepInsertStatement = conection.prepareStatement(query.toString());
            prepInsertStatement.executeUpdate();
            conection.close();
            prepInsertStatement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception when executing insert query");
            e.printStackTrace();
        }
    }


    /**
     * stergerea unui obiect din baza de date
     * @param abstractObject
     */
    public void delete(Object abstractObject) {
        String tableName = abstractObject.getClass().getSimpleName();
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM `" + tableName + "` WHERE ");

        Field[] allFields = abstractObject.getClass().getDeclaredFields();
        Field firstField = allFields[0];
        firstField.setAccessible(true);
        String fieldName = firstField.getName();
        query.append(fieldName).append(" = ");
        try {
            Object value = firstField.get(abstractObject);
            query.append(value);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at getting id value");
        }
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement prepDeleteStatement = con.prepareStatement(query.toString());
            prepDeleteStatement.executeUpdate();
            con.close();
            prepDeleteStatement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception when executing delete query");
            e.printStackTrace();

        }
    }

    /**
     * aceasta metoda realizeaza updatarea campurilor unui obiect in baza de date
     * @param abstractObject
     */

    public void update(Object abstractObject) {
        String tableName = abstractObject.getClass().getSimpleName();
        StringBuilder query = new StringBuilder();
        query.append("UPDATE `" + tableName + "` SET ");
        Field[] allFields = abstractObject.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < allFields.length - 1; i++) {
                allFields[i].setAccessible(true);
                query.append(allFields[i].getName());
                query.append(" = ");
                Object value = allFields[i].get(abstractObject);
                String fieldType = allFields[i].getType().getSimpleName();
                if (fieldType.equals("String"))
                    query.append("\"" + value + "\"");
                else
                    query.append(value);
                query.append(", ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eroare la update");
        }
        int lastFieldIndex = allFields.length - 1;
        allFields[lastFieldIndex].setAccessible(true);
        query.append(allFields[lastFieldIndex].getName());
        query.append(" = ");
        try {
            Object value = allFields[lastFieldIndex].get(abstractObject);
            String fieldType = allFields[lastFieldIndex].getType().getSimpleName();
            if (fieldType.equals("String"))
                query.append("\"" + value + "\"");
            else
                query.append(value);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error la update");
        }

        query.append(" WHERE ");
        Field firstField = allFields[0];
        firstField.setAccessible(true);
        String fieldName = firstField.getName();
        query.append(fieldName).append(" = ");
        try {
            Object value = firstField.get(abstractObject);
            query.append(value);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eroare la luarea id-ului");
        }
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement prepUpdateStatement = con.prepareStatement(query.toString());
            prepUpdateStatement.executeUpdate();
            con.close();
            prepUpdateStatement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception when executing update query");
            e.printStackTrace();
        }
    }

    /**
     * face select la toate obiectele din baza de date
     * @return
     */
    public List<T> findAll() {
        String tableName = type.getSimpleName();
        if (tableName.equalsIgnoreCase("Order")) {
            tableName = "`Order`";
        }

        String sql = "SELECT * FROM " + tableName;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * aceasta metoda creaza obiectele din rezultatele unei interogari
     * @param resultSet
     * @return
     */

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (Constructor constructor : ctors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalArgumentException | SecurityException | IllegalAccessException |
                 IntrospectionException | SQLException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * aceasta metoda gaseste un obiect in baza de date dupa ID
     * @param id
     * @return
     */

    public T findById(int id) {
        String tableName = type.getSimpleName();
        if (tableName.equalsIgnoreCase("Order")) {
            tableName = "`Order`";
        }

        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<T> resultList = createObjects(resultSet);
            if (!resultList.isEmpty()) {
                return resultList.get(0);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * creeaza interogare de tip SELECT
     * @param field
     * @return interogarea select, sirul de caractere care reprezinta selectul
     */

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }


    /**
     * aceasta metoda creaza un Jtable cu toate obiectele din baza de date
     * @return
     */


    public JTable createTable() {
        List<T> myList =findAll();
        if (!myList.isEmpty()) {
            int tableSize = myList.get(0).getClass().getDeclaredFields().length;
            String[] columnNames = new String[tableSize];
            int columnIndex = 0;
            for (Field currentField : myList.get(0).getClass().getDeclaredFields()) {
                currentField.setAccessible(true);
                try {
                    columnNames[columnIndex] = currentField.getName();
                    columnIndex++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            DefaultTableModel myModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            myModel.setColumnIdentifiers(columnNames);
            for (Object o : myList) {
                Object[] obj = new Object[tableSize];
                int col = 0;
                for (Field currentField : o.getClass().getDeclaredFields()) {
                    currentField.setAccessible(true);
                    try {
                        obj[col] = currentField.get(o);
                        col++;
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                myModel.addRow(obj);
            }
            JTable orderTable = new JTable(myModel);
            orderTable.setEnabled(true);
            orderTable.setVisible(true);
            return orderTable;
        }
        return null;
    }







}
