package com.e3i3.moduerp.common.handler;

import java.sql.*;
import java.util.UUID;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(UUID.class)
public class UUIDTypeHandler extends BaseTypeHandler<UUID> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String uuidString = rs.getString(columnName);
        return uuidString != null ? UUID.fromString(uuidString) : null;
    }

    @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String uuidString = rs.getString(columnIndex);
        return uuidString != null ? UUID.fromString(uuidString) : null;
    }

    @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String uuidString = cs.getString(columnIndex);
        return uuidString != null ? UUID.fromString(uuidString) : null;
    }
}
