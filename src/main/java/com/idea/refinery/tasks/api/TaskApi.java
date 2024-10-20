package com.idea.refinery.tasks.api;

import com.idea.refinery.tasks.entity.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

public interface TaskApi {

    @Operation(
        operationId = "getAllTasks",
        summary = "Get all tasks",
        description = "This API Fetches all tasks and their data the database",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success",content = {
                @Content(mediaType = "application/json", array =@ArraySchema(schema = @Schema(implementation = Task.class)))
            }),
            @ApiResponse(responseCode = "404", description = "No tasks in Database",content = {
                    @Content(mediaType = "application/json")
            })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/tasks",
            produces ={"application/json"}
    )
    default ResponseEntity<List<Task>> getAllTasks(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Operation(
            operationId = "getTaskById",
            summary = "Get a task by ID",
            description = "This API Fetches a task and its data from the database by tradeID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "No task with given ID in Database",content = {
                            @Content(mediaType = "application/json")
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/tasks/{id}",
            produces ={"application/json"}
    )
    default ResponseEntity<Task> getTaskById(
            @Parameter(name="id", description = "Task ID associated to the task", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Operation(
            operationId = "saveTask",
            summary = "Save Task",
            description = "This API saves a tasks and its data to the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "Unable to connect to Database",content = {
                            @Content(mediaType = "application/json")
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/tasks",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    default ResponseEntity<Task> saveTask(
            @Parameter(name="newTask", description = "Task as a JSON object", required = true) @RequestBody Task newTask){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(
            operationId = "updateTask",
            summary = "Updates Task",
            description = "This API updates a task with new data to the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "No task with given ID in Database",content = {
                            @Content(mediaType = "application/json")
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/tasks/{id}",
            produces ={"application/json"},
            consumes ={"application/json"}
    )
    default ResponseEntity<Task> updateTask(
            @Parameter(name="id", description = "Task ID associated to the task", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name="newTask", description = "Task as a JSON object", required = true) @RequestBody Task task){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
