package shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import shop.exception.DuplicateException;
import shop.exception.NotFoundException;
import shop.mapper.ProductMapper;
import shop.mybatis.MybatisClient;
import shop.vo.Product;

public class MybatisWarehouse implements GeneralWarehouse {

	private SqlSessionFactory factory;
	
	public MybatisWarehouse() {
		factory =MybatisClient.getFactory();
	}

	@Override
	public int add(Product product) throws DuplicateException {
		// 대상 제품의 존재 여부 확인
		if (isExists(product)) {
			throw new DuplicateException("add", product);
		}

		// 1. session 열기 : DML 작업은 auto-commit 활성화
		SqlSession session = factory.openSession(true);
		int addCnt = 0;

		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		try {
			// 3. mapper를 통해 추가 진행
			addCnt = mapper.insert(product);
		} finally {
			if (session != null)
				session.close();
		}

		return addCnt;
	}

	@Override
	public Product get(Product product) throws NotFoundException {
		// 대상 제품의 존재 여부 확인
		if (!isExists(product)) {
			throw new NotFoundException("get", product);
		}

		// 1. session 열기 : DQL 작업은 non auto-commit으로 오픈해도 상관 없음
		SqlSession session = factory.openSession(false);
		Product found = null;

		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		try {
			// 3. mapper를 통해 조회 진행
			found = mapper.selectOne(product);
		} finally {
			if (session != null)
				session.close();
		}

		return found;
	}

	@Override
	public int set(Product product) throws NotFoundException {
		// 대상 제품의 존재 여부 확인
		if (!isExists(product)) {
			throw new NotFoundException("set", product);
		}

		// 1. session 열기 : DML 작업은 auto-commit 활성화
		SqlSession session = factory.openSession(true);
		int setCnt = 0;

		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		try {
			// 3. mapper를 통해 수정 진행
			setCnt = mapper.update(product);
		} finally {
			if (session != null)
				session.close();
		}

		return setCnt;
	}

	@Override
	public int remove(Product product) throws NotFoundException {
		// 대상 제품의 존재 여부 확인
		if (product != null && !isExists(product)) {
			throw new NotFoundException("remove", product);
		}

		// 1. session 열기 : DML 작업은 auto-commit 활성화
		SqlSession session = factory.openSession(true);
		int rmCnt = 0;

		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		try {
			// 3. mapper를 통해 삭제 진행
			rmCnt = mapper.delete(product);
		} finally {
			if (session != null)
				session.close();
		}

		return rmCnt;
	}

	@Override
	public List<Product> getAllProducts() {

		// 1. session 열기
		SqlSession session = factory.openSession();

		// mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		List<Product> products = null;

		try {
			products = mapper.selectAll();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return products;
	}

	// isExists 지원 메소드 작성
	private boolean isExists(Product product) {
		boolean isExist = false;

		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();

		// 2. Mapper 인터페이스 객체를 sesion에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		// 3. mapper 객체에 메소드 호출로 쿼리 실행
		try {
			String prodCode = mapper.isExists(product);
			if (prodCode != null) {
				isExist = true;
			}
		} finally {
			if (session != null)
				session.close();
		}
		return isExist;
	}

}
