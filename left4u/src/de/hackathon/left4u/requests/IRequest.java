package de.hackathon.left4u.requests;

/**
 * @author <a href="mailto:meder@adobe.com">Nils Meder</a>
 */
public interface IRequest<T> {

	T execute();
}
