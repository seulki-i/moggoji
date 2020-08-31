package dev.mvc.actor;

import dev.mvc.actor.ActorVO;

public interface ActorDAOInter {
 
  /**
   * <Xmp>
   *  배우정보 입력
   *  <insert id="create" parameterType="ActorVO">
   * </Xmp>
   * @param ActorVO
   * @return 처리된 레코드 개수
   */
  public int create(ActorVO actorVO);
   
  
}
