package pri.yqx.json;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PicUrlJsonHandler extends BaseTypeHandler<List<PicUrl>> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<PicUrl> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public List<PicUrl> getNullableResult(ResultSet rs, String columnName) throws SQLException {

        return JSON.parseArray(rs.getString(columnName),PicUrl.class);
    }

    @Override
    public List<PicUrl> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return JSON.parseArray(rs.getString(columnIndex),PicUrl.class);
    }

    @Override
    public List<PicUrl> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return JSON.parseArray(cs.getString(columnIndex),PicUrl.class);
    }
}
