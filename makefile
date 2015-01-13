# Ant sucks, and so does Eclipse.

all: run-meh

CORE_DIR=core
CORE_CLASSES=MehDriver \
             MehController
CORE_SRC=$(patsubst %, $(CORE_DIR)/%.java, $(CORE_CLASSES))

VIEW_DIR=view
VIEW_CLASSES=EdgeView \
             GraphView \
						 IGraphViewBuddy \
						 MehView \
						 NodeView
VIEW_SRC=$(patsubst %, $(VIEW_DIR)/%.java, $(VIEW_CLASSES))

GRAPH_DIR=graph
GRAPH_CLASSES=Graph \
              GraphLibrary \
							GraphNode
GRAPH_SRC=$(patsubst %, $(GRAPH_DIR)/%.java, $(GRAPH_CLASSES))

MEH_SRC=$(GRAPH_SRC) $(VIEW_SRC) $(CORE_SRC)

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

meh: $(MEH_SRC) | $(BUILD_DIR_CREATED)
	javac $^ -d $(BUILD_DIR)

run-meh: meh
	cd $(BUILD_DIR); java $(MAIN_CLASS)

clean:
	rm -frd $(BUILD_DIR)

graph-test: $(GRAPH_DIR)/*.java | $(BUILD_DIR_CREATED)
	javac -cp $(BUILD_DIR):$(MCGOO_JAR) $^ -d $(BUILD_DIR)

run-graph-test: graph-test
	cd $(BUILD_DIR); java -cp .:$(MCGOO_JAR) graph.GraphTest