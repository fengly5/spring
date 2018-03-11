/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.validacion;

import java.util.List;

/**
 *{"multicast_id":4993241495344746770,
 * "success":0,
 * "failure":1,
 * "canonical_ids":0,
 * "results":[{"error":"InvalidRegistration"}]}
 * @author jcpm0
 */
public class RespuestaFCM {
  private String multicast_id;
  private int success;
  private int failure;
  private int canonical_ids;
  private List<Results> results;

  @Override
  public String toString() {
    return "RespuestaFCM{" + "multicast_id=" + multicast_id + ", success=" + success + ", failure=" + failure + ", canonical_ids=" + canonical_ids + ", results=" + results + '}';
  }

  public RespuestaFCM() {
  }

  
  public String getMulticast_id() {
    return multicast_id;
  }

  public void setMulticast_id(String multicast_id) {
    this.multicast_id = multicast_id;
  }

  public int getSuccess() {
    return success;
  }

  public void setSuccess(int success) {
    this.success = success;
  }

  public int getFailure() {
    return failure;
  }

  public void setFailure(int failure) {
    this.failure = failure;
  }

  public int getCanonical_ids() {
    return canonical_ids;
  }

  public void setCanonical_ids(int canonical_ids) {
    this.canonical_ids = canonical_ids;
  }

  public List<Results> getResults() {
    return results;
  }

  public void setResults(List<Results> results) {
    this.results = results;
  }

  private static class Results {
    private String error;

    public String getError() {
      return error;
    }

    public void setError(String error) {
      this.error = error;
    }

    @Override
    public String toString() {
      return "Results{" + "error=" + error + '}';
    }
    
    public Results() {
    }
  }
}
