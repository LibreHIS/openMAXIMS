package ims.framework.interfaces;

import java.io.Serializable;

public interface IIDGenerator extends Serializable
{
	int nextId();
	void reset();
}
