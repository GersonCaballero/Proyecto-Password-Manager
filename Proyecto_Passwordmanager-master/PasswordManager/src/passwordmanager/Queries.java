/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.sqlite.SQLiteDataSource;

/**
 *
 * @author Victor Sandoval
 */
public class Queries {
    
    private DataSource ds;
    private Connection conn;
    private Sha512 encrypt;

    public Queries() {
        ds = Conexion.getSQLLiteDataSource();
        conn = null;
    }

    private Connection connectDB() {
        try {
            conn = this.ds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private void disconnectDB(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validarUser(String user) {

        conn = connectDB();
        String query = " Select UserEmail from Users;";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;

        try {
            consulta = conn.prepareStatement(query);
            resultadotabla = consulta.executeQuery();
            while (resultadotabla.next()) {
                if (user.equals(resultadotabla.getString(1))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    
    public boolean validarPassword(String password, String email) {
        
        conn = connectDB();
        String query = " Select UserPassword from Users where UserEmail = ?;";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;

        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, email);
            resultadotabla = consulta.executeQuery();

            if (password.equals(resultadotabla.getString(1))) {
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    
    public String retornarNombre(String userEmail) {

        conn = connectDB();
        String query = "Select UserName from Users where UserEmail = ?;";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String nombre = "";

        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, userEmail);
            resultadotabla = consulta.executeQuery();
            nombre = resultadotabla.getString(1);

        } catch (SQLException e) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return nombre.toUpperCase();
    }
    
    public int retornarID(String userEmail) {

        conn = connectDB();
        String query = "Select UserID from Users where UserEmail = ?;";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        int id = 0;

        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, userEmail);
            resultadotabla = consulta.executeQuery();
            id = resultadotabla.getInt(1);

        } catch (SQLException e) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return id;
    }
    
    public String retornarPreguntaSeguridad(String userEmail) {

        conn = connectDB();
        String query = "Select UserQuestion from Users where UserEmail = ?;";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String question = "";

        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, userEmail);
            resultadotabla = consulta.executeQuery();
            question = resultadotabla.getString(1);

        } catch (SQLException e) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return question;
    }
    
    public boolean validarRespuestaSeguridad(String userEmail, String userAnswer) {

        conn = connectDB();
        String query = " Select UserAnswer from Users where UserEmail = ?;";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;

        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, userEmail);
            resultadotabla = consulta.executeQuery();
            if (userAnswer.equalsIgnoreCase(resultadotabla.getString(1))) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    
    public void insertarUsuario(String userName, String userEmail, String userPassword, String userQuestion, String userAnswer) {
                
        conn = connectDB();
        String query = "Insert into Users(UserName, UserEmail, UserPassword, UserQuestion, UserAnswer) Values(?, ?, ?, ?, ?);";
        PreparedStatement consulta = null;

        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, userName);
            consulta.setString(2, userEmail);
            consulta.setString(3, userPassword);
            consulta.setString(4, userQuestion);
            consulta.setString(5, userAnswer);

            boolean resultado = consulta.execute();

            if (resultado) {
                System.out.println("**Su Usuario no pudo ser registrado.");
            } else {
                System.out.println("Se ha registrado su Usuario exitosamente!");
            }

        } catch (SQLException e) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void insertarCuenta(String siteName, String siteUser, String sitePassword, int userID) {

        conn = connectDB();
        String query = "Insert into Sites(SiteName, SiteUser, SitePassword, UserId) Values(?, ?, ?, ?);";
        PreparedStatement consulta = null;

        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, siteName);
            consulta.setString(2, siteUser);
            consulta.setString(3, sitePassword);
            consulta.setInt(4, userID);
            

            boolean resultado = consulta.execute();

            if (resultado) {
                System.out.println("**Su Cuenta no pudo ser guardada.");
            } else {
                System.out.println("Su Cuenta fue guardada exitosamente!");
            }

        } catch (SQLException e) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void mostrarCuentas(int userID) {
        conn = connectDB();
        String query = "Select SiteName, SiteUser, SitePassword From Sites where UserID = ?;";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        try {
            consulta = conn.prepareStatement(query);
            consulta.setInt(1, userID);
            resultadotabla = consulta.executeQuery();
            
            while (resultadotabla.next()) {
                System.out.printf("%-24s%-40s%-30s%n", resultadotabla.getString(1), resultadotabla.getString(2), resultadotabla.getString(3));
            }
            
        } catch (SQLException e) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void insertarNuevaPassword(String newPass, String userEmail) {
        
        conn = connectDB();
        String query = "UPDATE Users SET UserPassword = ? WHERE UserEmail = ?;";
        PreparedStatement consulta = null;

        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, newPass);
            consulta.setString(2, userEmail);
            
            boolean resultado = consulta.execute();

            if (resultado) {
                System.out.println("**No se pudo guardar su nueva contraseña");
            } else {
                System.out.println("Su nueva Contraseña fue guardada exitosamente");
            }

        } catch (SQLException e) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
