# Ant sucks, and so does Eclipse.

all: run-meh

# FILES

CORE_DIR=core
VIEW_DIR=view
GRAPH_DIR=graph

CORE_CLASSES=$(patsubst %, $(CORE_DIR)/%.java, MehDriver MehController)
VIEW_CLASSES=$(patsubst %, $(VIEW_DIR)/%.java, MehView GraphView)
GRAPH_CLASSES=$(patsubst %, $(GRAPH_DIR)/%.java, Graph GraphLibrary GraphNode)

SOURCE_CLASSES=$(CORE_CLASSES) $(VIEW_CLASSES) $(GRAPH_CLASSES)

SHELL=sh

SRC_DIRS=$(CORE_DIR) $(VIEW_DIR) $(GRAPH_DIR)
BUILD_DIR=build
BUILD_DIR_CREATED=$(BUILD_DIR)/tuna

MAIN_CLASS=core.MehDriver

MCGOO_JAR=/usr/local/lib/mcgoo.jar

test: run-graph-test

$(BUILD_DIR_CREATED):
	mkdir $(BUILD_DIR)
	touch $@

meh: $(SOURCE_CLASSES) | $(BUILD_DIR_CREATED)
	javac $^ -d $(BUILD_DIR)

run-meh: meh
	cd $(BUILD_DIR); java $(MAIN_CLASS)

clean:
	rm -frd $(BUILD_DIR)

graph-test: $(GRAPH_DIR)/*.java | $(BUILD_DIR_CREATED)
	javac -cp $(BUILD_DIR):$(MCGOO_JAR) $^ -d $(BUILD_DIR)

run-graph-test: graph-test
	cd $(BUILD_DIR); java -cp .:$(MCGOO_JAR) graph.GraphTest