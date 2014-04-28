package dbmanager;

import java.sql.ResultSet;
import java.util.ArrayList;

import warpper.Word;

public class WLibManager {
	DBConnection dbcon = new DBConnection();
	
	public Boolean Add(Word word){
		return dbcon.update("insert into wordlib(word,length,category,part) values(?,?,?,?)",
				word.getValue(),String.valueOf(word.getLength()),word.getCategory(),word.getPart()) > 0;
	}
	
	public Boolean Update(String key,String keyValue,String aim, String aimValue){
		return dbcon.update("update wordlib set "+aim+"=? where "+key+"=?", aimValue, keyValue) > 0;
	}
	
	public ArrayList<Word> queryByKey(String key,String value){
		ArrayList<Word> list = new ArrayList<Word>();
		ResultSet rs = dbcon.query("select * from wordlib where "+key+"=?",value);
		try {
			while (rs.next()) {
				Word w = new Word(rs.getString("word"), Integer.parseInt(rs.getString("length")),
						rs.getString("category"), rs.getString("part"));
				w.setID(Integer.parseInt((rs.getString("id"))));
				list.add(w);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbcon.closeAll();
		}
		return list;
	}
	
	public ArrayList<Word> queryAll(){
		ArrayList<Word> list = new ArrayList<Word>();
		ResultSet rs = dbcon.query("select * from wordlib");
		try {
			while (rs.next()) {
				Word w = new Word(rs.getString("word"), Integer.parseInt(rs.getString("length")),
						rs.getString("category"), rs.getString("part"));
				w.setID(Integer.parseInt((rs.getString("id"))));
				list.add(w);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbcon.closeAll();
		}
		return list;
	}

	public int delete(String key, String value) {
		// TODO Auto-generated method stub
		return dbcon.update("delete from wordlib where "+key+"=?", value);
	}

}
