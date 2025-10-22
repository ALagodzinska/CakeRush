package persistence;

import org.json.JSONObject;

// Adapted from: 
//   Project Title: JsonSerializationDemo
//   Author: CPSC210
//   Type: source code
//   URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Interface for objects that can be converted to JSON format.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();    
}