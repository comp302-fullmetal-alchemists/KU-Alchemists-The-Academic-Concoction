package system.domain.interfaces;

import system.domain.ArtifactCard;

public interface IUsingBehavior {

	/* 
	 * UsingBehavior utilize Strategy Pattern for the usage of artifact cards. This interface will be implemented
     * by the LimitedArtifactUsage class and UnlimitedArtifactUsage class. According to their usage differences 
	 * */
	
    void useArtifact(ArtifactCard ac);

}