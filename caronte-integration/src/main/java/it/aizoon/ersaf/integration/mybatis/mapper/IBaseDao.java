package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.GenericExample;

/**
 * @author francesco.giuffrida
 *
 */
public interface IBaseDao<E extends BaseDto, K extends GenericExample> {
	
	public abstract long countByExample(K example);

	public abstract int deleteByExample(K example);

	public abstract int deleteByPrimaryKey(Long idClasse);

	public abstract int insert(E record);

	public abstract int insertSelective(E record);

	public abstract List<E> selectByExample(K example);

	public abstract E selectByPrimaryKey(Long idClasse);

	public abstract int updateByExampleSelective(@Param("record") E record, @Param("example") K example);

	public abstract int updateByExample(@Param("record") E record, @Param("example") K example);

	public abstract int updateByPrimaryKeySelective(E record);

	public abstract int updateByPrimaryKey(E record); 
}
