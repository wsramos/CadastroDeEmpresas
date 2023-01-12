package br.com.cadastrarempresas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.cadastrarempresas.model.dao.dbaccess.DB;
import br.com.cadastrarempresas.model.dao.dbaccess.DbException;
import br.com.cadastrarempresas.model.dao.interfaces.EmpresaDao;
import br.com.cadastrarempresas.model.entitites.Empresa;

public class EmpresaDaoJDBC implements EmpresaDao{

		private Connection conn;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		public EmpresaDaoJDBC() {
		}
		
		public EmpresaDaoJDBC(Connection conn) {
			this.conn = conn;
		}
		
		@Override
		public void insert(Empresa obj) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("BEGIN CADASTRAR_EMPRESA_PAC.CADASTRAR_EMPRESA(?,?,?); END;",
						Statement.RETURN_GENERATED_KEYS);
				
				st.setString(1, obj.getNome());
				st.setString(2, obj.getCnpj());
				st.setDate(3, new java.sql.Date(obj.getDataAbertura().getTime()));
				int rowsAffected = st.executeUpdate();
				
				if(rowsAffected > 0) {
					rs = st.getGeneratedKeys();
					while (rs.next()) {
						int id = rs.getInt(1);
						System.out.println("Done! ID = " + id);
					}
				}else {
					System.out.println("No rows affected");
				}	
			}		
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		}

		@Override
		public void updateAtributteById(Integer id, String atributo, String valorAtributo) {
			PreparedStatement st = null;
			
				try {
					st = conn.prepareStatement("BEGIN CADASTRAR_EMPRESA_PAC.ALTERAR_EMPRESA(?,?,?); END;");
					
					st.setInt(1, id);
					st.setString(2, atributo);
					st.setString(3, valorAtributo);
					st.executeUpdate();	
				}		
				catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
				finally {
					DB.closeStatement(st);
				}	
		}
		
		@Override
		public void updateAllById(Empresa obj) {
			PreparedStatement st = null;
			
				try {
					st = conn.prepareStatement("BEGIN CADASTRAR_EMPRESA_PAC.ALTERAR_EMPRESA(?,?,?,?); END;");
					
					st.setInt(1, obj.getId());
					st.setString(2, obj.getNome());
					st.setString(3, obj.getCnpj());
					st.setDate(3, new java.sql.Date(obj.getDataAbertura().getTime()));
					st.executeUpdate();	
				}		
				catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
				finally {
					DB.closeStatement(st);
				}	
		}

		@Override
		public void deleteById(Integer id) {
			PreparedStatement st = null;
			
			try {
				st = conn.prepareStatement("BEGIN CADASTRAR_EMPRESA_PAC.EXCLUIR_EMPRESA(?); END;");
				st.setInt(1, id);
				st.executeUpdate();	
			}		
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
			}
		}

		@Override
		public Empresa findById(Integer id) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT * FROM EMPRESA WHERE Id = ?");
				
				st.setInt(1, id);
				rs = st.executeQuery();
				
				if (rs.next()) {
					Empresa emp = instanteateEmpresa(rs);
					return emp;
				}
				return null;			
			}		
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		}

		private Empresa instanteateEmpresa(ResultSet rs) throws SQLException{
			Empresa obj = new Empresa();
			obj.setId(rs.getInt("Id"));
			obj.setNome(rs.getString("Nome"));
			obj.setCnpj(rs.getString("Cnpj"));
			obj.setDataAbertura(new java.util.Date(rs.getDate("Data_Abertura").getTime()));			
			return obj;
		}

		@Override
		public List<Empresa> findAll() {
			PreparedStatement st = null;
			ResultSet rs = null;
			List<Empresa> empresas = new ArrayList<>();
			try {
				st = conn.prepareStatement("SELECT * FROM EMPRESA");

				rs = st.executeQuery();
				
				while (rs.next()) {
					empresas.add(instanteateEmpresa(rs));
				}
				return empresas;			
			}		
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		}

	}

