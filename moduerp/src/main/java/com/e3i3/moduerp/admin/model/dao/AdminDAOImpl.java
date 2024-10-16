package com.e3i3.moduerp.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.e3i3.moduerp.admin.model.dto.AdminDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "AdminMapper";

    @Override
    public List<AdminDTO> selectAllAdmins() {
        return sqlSession.selectList(namespace + ".selectAllAdmins");
    }

	@Override
	public List<AdminDTO> getAdminByBizNumberDate(String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectAdminByBizNumberDate",
				Map.of("filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<AdminDTO> getAdminByEmpNameDate(String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectAdminByEmpNameDate",
				Map.of("filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<AdminDTO> getAdminByEmpEmailDate(String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectAdminByEmpEmailDate",
				Map.of("filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<AdminDTO> getAdminByUuidDate(String filterText, String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectAdminByUuidDate",
				Map.of("filterText", filterText, "startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<AdminDTO> getAdminByBizNumber(String filterText) {
		return sqlSession.selectList(namespace + ".selectAdminByBizNumber",
				Map.of("filterText", filterText));
	}

	@Override
	public List<AdminDTO> getAdminByEmpName(String filterText) {
		return sqlSession.selectList(namespace + ".selectAdminByEmpName",
				Map.of("filterText", filterText));
	}

	@Override
	public List<AdminDTO> getAdminByEmpEmail(String filterText) {
		return sqlSession.selectList(namespace + ".selectAdminByEmpEmail",
				Map.of("filterText", filterText));
	}

	@Override
	public List<AdminDTO> getAdminByUuid(String filterText) {
		return sqlSession.selectList(namespace + ".selectAdminByUuid",
				Map.of("filterText", filterText));
	}

	@Override
	public List<AdminDTO> getAdminByFilterOnlyDate(String startDate, String endDate) {
		return sqlSession.selectList(namespace + ".selectAdminByFilterOnlyDate",
				Map.of("startDate", startDate, "endDate", endDate));
	}

	@Override
	public List<AdminDTO> getAdminByFilterStartDate(String startDate) {
		return sqlSession.selectList(namespace + ".selectAdminByFilterStartDate",
				Map.of("startDate", startDate));
	}

	@Override
	public List<AdminDTO> getAdminByFilterEndDate(String endDate) {
		return sqlSession.selectList(namespace + ".selectAdminByFilterEndDate",
				Map.of("startDate", endDate));
	}
}
